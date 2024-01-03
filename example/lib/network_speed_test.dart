// import 'package:flutter/material.dart';
// import 'package:wolkezoo_plugin/tools/network_speed_tools.dart';
//
// void main() {
//   runApp(const MyApp());
// }
//
// class MyApp extends StatefulWidget {
//   const MyApp({super.key});
//
//   @override
//   State<MyApp> createState() => _MyAppState();
// }
//
// class _MyAppState extends State<MyApp> {
//   String _platformVersion = 'Unknown';
//
//   @override
//   void initState() {
//     super.initState();
//   }
//
//   String speed = "";
//
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       home: Scaffold(
//         appBar: AppBar(
//           title: const Text('Plugin example app'),
//         ),
//         body: Center(
//           child: Column(
//             crossAxisAlignment: CrossAxisAlignment.center,
//             mainAxisAlignment: MainAxisAlignment.center,
//             children: [
//               InkWell(
//                 onTap: () {
//                   NetworkSpeedTools.openNetworkSpeedListen((speed) {
//                     setState(() {
//                       this.speed = speed;
//                     });
//                   });
//                 },
//                 child: const Center(
//                   child: Text("开启网速监听"),
//                 ),
//               ),
//               SizedBox(
//                 height: 100,
//               ),
//               InkWell(
//                 onTap: () {
//                   NetworkSpeedTools.closeNetworkSpeedListen();
//                 },
//                 child: const Center(
//                   child: Text("关闭网速监听"),
//                 ),
//               ),
//               SizedBox(
//                 height: 100,
//               ),
//               Text("当前网速 >> $speed")
//             ],
//           ),
//         ),
//       ),
//     );
//   }
// }
