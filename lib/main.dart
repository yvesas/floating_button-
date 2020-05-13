import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Home(),
    );
  }
}

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  static const plataform = const MethodChannel("floating_btn");

  int count = 0;
  bool isShowing = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    
    plataform.setMethodCallHandler((call){
        if(call.method == "touch"){
            setState(() {
              count += 1;
            });
        }
    });

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Floating Button"),
      centerTitle: true),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            Text("$count", textAlign: TextAlign.center, style: TextStyle(fontSize:50)),
            RaisedButton(
              child: Text("Create"),
              onPressed: (){
                plataform.invokeMethod("create");
              },
            ),
            RaisedButton(
              child: Text("Show"),
              onPressed: () {
                  plataform.invokeMethod("show");
              },
            ),
            RaisedButton(
              child: Text("Hide"),
              onPressed: () {
                plataform.invokeMethod("hide");
              },
            ),
            RaisedButton(
              child: Text("has Showing?"),
              onPressed: () {
                plataform.invokeMethod("showing").then((value){
                    print(value);
                    setState(() {
                      isShowing = value;
                    });
                });
              },
            ),
            RichText(
              text: TextSpan(
                text: 'Visivel: ',
                style: TextStyle(fontSize:20, color: Colors.blueAccent),
                children: <TextSpan>[
                  TextSpan(text: "$isShowing", style: TextStyle(fontWeight: FontWeight.bold))
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
