import 'package:flutter/material.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();

}

class _LoginScreenState extends State<LoginScreen> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();

  Future<void> _login() async {
    final username = _usernameController.text;
    final password = _passwordController.text;
    // API call here
    print(username + password);
    Navigator.pushReplacementNamed(context, '/cam');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Container(
        color: Colors.white,
        constraints: BoxConstraints.expand(),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Container(
              color: Colors.yellow,
              constraints: BoxConstraints.expand(height: 75),
              child: Center(
                child: Image.asset('assets/logo.png'),
              ),
            ),
            SizedBox(height: 65),
            Text(
              "Log into Snapchat",
              style: TextStyle(
                color: Colors.black,
                fontSize: 25.0,
                fontWeight: FontWeight.bold,
              ),
            ),
            SizedBox(
              height: 25,
            ),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 40.0, vertical: 8.0),
              child: TextField(
                controller: _usernameController,
                cursorColor: Colors.green,
                cursorWidth: 3.0,
                decoration: InputDecoration(
                  labelText: "USER NAME OR EMAIL",
                  floatingLabelBehavior: FloatingLabelBehavior.always,
                  labelStyle: TextStyle(color: Colors.grey, fontSize: 13.0),
                  focusColor: Colors.black,
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey),
                  ),
                ),
              ),
            ),
            SizedBox(
              height: 25,
            ),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 40.0, vertical: 10.0),
              child: TextField(
                controller: _passwordController,
                obscureText: true,
                cursorColor: Colors.green,
                cursorWidth: 3.0,
                decoration: InputDecoration(
                  labelText: "PASSWORD",
                  floatingLabelBehavior: FloatingLabelBehavior.always,
                  labelStyle: TextStyle(color: Colors.grey, fontSize: 13.0),
                  focusColor: Colors.grey,
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: Colors.grey),
                  ),
                ),
              ),
            ),
            SizedBox(
              height: 25,
            ),
            GestureDetector(
              onTap: _login,
              child: Container(
                  width: 200,
                  height: 50,
                  padding: EdgeInsets.symmetric(horizontal: 25),
                  decoration: BoxDecoration(
                    color: Colors.blue,
                    borderRadius: BorderRadius.circular(25.0),
                  ),
                  child: Center(
                      child: Text(
                        "Log In",
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 25.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ))),
            ),
          ],
        ),
      ),
    );
  }
}