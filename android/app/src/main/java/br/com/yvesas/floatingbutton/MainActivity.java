package br.com.yvesas.floatingbutton;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.Screen;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {

    private static final  String CHANNEL  = "floating_btn";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MethodChannel channel = new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL);

        channel.setMethodCallHandler(
                (call, result) -> {
                    switch(call.method){
                        case "create":
                            ImageView imageView = new ImageView(getApplicationContext());
                            imageView.setImageResource(R.drawable.mario);

                            FloatWindow.with(getApplicationContext())
                                    .setView(imageView)
                                    .setWidth(Screen.width, 0.15f)
                                    .setHeight(Screen.width, 0.15f)
                                    .setX(Screen.width, 0.8f)
                                    .setY(Screen.height, 0.3f)
                                    .setDesktopShow(true)
                                    .build();

                            imageView.setOnClickListener(v -> {
                                channel.invokeMethod("touch", null);
                            });

                            break;
                        case "show":
                            FloatWindow.get().show();
                            break;
                        case "hide":
                            FloatWindow.get().hide();
                            break;
                        default:
                            result.notImplemented();
                    };
                }
        );
    }

//    @Override
//    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
//        super.configureFlutterEngine(flutterEngine);
//        MethodChannel channel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL);
//
//        channel.setMethodCallHandler(
//                (call, result) -> {
//                    switch(call.method){
//                        case "create":
//                            ImageView imageView = new ImageView(getApplicationContext());
//                            imageView.setImageResource(R.drawable.mario);
//
//                            FloatWindow.with(getApplicationContext())
//                                    .setWidth(Screen.width, 0.15f)
//                                    .setHeight(Screen.width, 0.15f)
//                                    .setX(Screen.width, 0.8f)
//                                    .setY(Screen.height, 0.3f)
//                                    .setDesktopShow(true)
//                                    .build();
//                            imageView.setOnClickListener(v -> {
//                                channel.invokeMethod("touch", null);
//                            });
//
//                            break;
//                        case "show":
//                            FloatWindow.get().show();
//                            break;
//                        case "hide":
//                            FloatWindow.get().hide();
//                            break;
//                         default:
//                             result.notImplemented();
//                    };
//                }
//        );
//
//    }

}
