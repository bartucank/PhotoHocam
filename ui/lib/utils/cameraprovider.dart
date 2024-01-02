import 'package:camera/camera.dart';
import 'package:flutter/foundation.dart';

class CameraProvider extends ChangeNotifier{
  late CameraController cameraController;
  late List<CameraDescription> cameraList;

  void init() async {
    cameraList = await availableCameras();
    cameraController = CameraController(cameraList.first, ResolutionPreset.ultraHigh,
        enableAudio: true);
    cameraController.initialize().then((_) {
      notifyListeners();
    });
    notifyListeners();
  }

}