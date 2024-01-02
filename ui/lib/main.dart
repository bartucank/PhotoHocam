import 'package:flutter/material.dart';
import 'package:photohocamui/pages/SplashScreen.dart';
import 'package:photohocamui/utils/cameraprovider.dart';
import 'package:provider/provider.dart';
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<CameraProvider>.value(value: CameraProvider()..init(),
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'CNG 495 ',
        theme: ThemeData(
          primarySwatch: Colors.yellow,
        ),
        home: const SplashScreen(),
        routes: {
          '/login': (context) =>  SplashScreen(),
          '/register': (context) => SplashScreen(),
          '/cam': (context) => SplashScreen(),
        },
      ),
    );

  }
}
