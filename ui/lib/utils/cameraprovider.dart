

import 'package:camera/camera.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter_cache_manager/flutter_cache_manager.dart';
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

  secondCamera() {
    final currentCamera = cameraController.description.lensDirection;
    for (var cam in cameraList) {
      if (cam.lensDirection != currentCamera) {
        cameraController = CameraController(cam, ResolutionPreset.ultraHigh,
            enableAudio: true);
        cameraController.initialize().then((_) {
          notifyListeners();
        });
        break;
      }
    }
    return;
  }


  CameraController getController() => cameraController;
}