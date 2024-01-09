import 'package:flutter/material.dart';
import 'package:photohocamui/pages/CamPage.dart';
import 'package:photohocamui/pages/FriendPage.dart';
import 'package:photohocamui/pages/FriendRequestPage.dart';
import 'package:photohocamui/pages/MessagePage.dart';
import 'package:photohocamui/pages/SplashScreen.dart';
import 'package:photohocamui/pages/login.dart';
import 'package:photohocamui/pages/register.dart';
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
          '/login': (context) =>  LoginScreen(),
          '/splash': (context) =>  SplashScreen(),
          '/register': (context) => RegisterScreen(),
          '/cam': (context) => CamPage(),
          '/unfriend': (context) => FriendPage(),
          '/friendrequest': (context) => FriendRequestPage(),
          '/message': (context) => MessagesPage(),

        },
      ),
    );

  }
}
