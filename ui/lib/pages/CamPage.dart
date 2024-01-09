
import 'dart:convert';
import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';
import 'package:photohocamui/utils/ApiService.dart';
import 'package:provider/provider.dart';

import '../utils/cameraprovider.dart';
import 'ViewSnapPage.dart';
class CamPage extends StatefulWidget {
  @override
  _CamPageState createState() => _CamPageState();
}

class _CamPageState extends State<CamPage> {

  void logout(){
    _apiService.logout();
    Navigator.pushNamed(context, '/splash');
  }
  final ApiService _apiService = ApiService();
  late String _capturedImagePath;




  Future<void> _takePicture(CameraController cameraController) async {
    final XFile? capturedImage = await cameraController.takePicture();
    if (capturedImage != null) {
      _capturedImagePath = capturedImage.path;

      // Convert the captured image to base64
      final List<int> imageBytes = await File(_capturedImagePath).readAsBytes();
      final String base64String = base64Encode(imageBytes);


      // Navigate to the view page
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => ViewSnapPage(imagePath: base64String),
        ),
      );
    }
  }



  @override
  Widget build(BuildContext context) {
    final CameraProvider camController = Provider.of<CameraProvider>(context);

    var size = MediaQuery.of(context).size;
    if (camController.getController() != null) {
      if (!camController.getController().value.isInitialized) {
        return Scaffold(
          backgroundColor: Colors.black,
          body: Center(
            child: CircularProgressIndicator(),
          ),
        );
      }
      return SafeArea(

        child: Stack(
          children: <Widget>[
            Container(
                width: size.width,
                height: size.height,
                child: GestureDetector(
                    onDoubleTap: () => camController.secondCamera(),
                    child: CameraPreview(camController.getController()))),

            Positioned(
                right: 20,
                top: 80,
                child: GestureDetector(
                  onTap: ()=>camController.secondCamera(),
                  child: Container(
                    width: 35,
                    height: 35,
                    decoration: new BoxDecoration(
                        color: Colors.grey.shade200,
                        shape: BoxShape.circle
                    ),
                    child: Icon(Icons.switch_camera_outlined, color: Colors.black,size: 25,),
                  ),
                )
            ),


            Positioned(
                right: 20,
                top: 40,
                child: GestureDetector(
                  onTap: ()=>Navigator.pushNamed(context, '/unfriend'),
                  child: Container(
                    width: 35,
                    height: 35,
                    decoration: new BoxDecoration(
                        color: Colors.grey.shade200,
                        shape: BoxShape.circle
                    ),
                    child: Icon(Icons.add, color: Colors.black,size: 25,),
                  ),
                )
            ),
            Positioned(
                right: 60,
                top: 40,
                child: GestureDetector(
                  onTap: ()=>Navigator.pushNamed(context, '/friendrequest'),
                  child: Container(
                    width: 35,
                    height: 35,
                    decoration: new BoxDecoration(
                        color: Colors.grey.shade200,
                        shape: BoxShape.circle
                    ),
                    child: Icon(Icons.person, color: Colors.black,size: 25,),
                  ),
                )
            ),
            Positioned(
                right: 100,
                top: 40,
                child: GestureDetector(
                  onTap: ()=>Navigator.pushNamed(context, '/message'),
                  child: Container(
                    width: 35,
                    height: 35,
                    decoration: new BoxDecoration(
                        color: Colors.grey.shade200,
                        shape: BoxShape.circle
                    ),
                    child: Icon(Icons.messenger_outlined, color: Colors.black,size: 25,),
                  ),
                )
            ),
            Positioned(
                left: 20,
                top: 40,
                child: GestureDetector(
                  onTap: ()=> logout()                  ,
                  child: Container(
                    width: 35,
                    height: 35,
                    decoration: new BoxDecoration(
                        color: Colors.grey.shade200,
                        shape: BoxShape.circle
                    ),
                    child: Icon(Icons.logout, color: Colors.black,size: 25,),
                  ),
                )
            ),
            Positioned(
              left: MediaQuery.of(context).size.width / 2 -35,
              bottom: 10,
              child: GestureDetector(
                onTap: () {
                  if (camController.getController() != null &&
                      camController.getController().value.isInitialized) {
                    _takePicture(camController.getController());
                  }
                },
                child: Container(
                  width: 70,
                  height: 70,
                  decoration: BoxDecoration(
                    shape: BoxShape.circle,
                    color: Colors.transparent,
                    border: Border.all(
                      color: Colors.grey.shade200,
                      width: 2.0,
                    ),
                  ),
                  child: Center(
                    child: Icon(
                      Icons.circle,
                      color: Colors.white,
                      size: 40,
                    ),
                  ),
                ),
              ),
            ),



          ],
        ),
      );
    } else
      return Scaffold(
        backgroundColor: Colors.white,
      );
  }
}

