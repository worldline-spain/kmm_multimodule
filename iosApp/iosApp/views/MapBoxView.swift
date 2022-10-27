//
//  MapBoxView.swift
//  iosApp
//
//  Created by Daniel Llanos Muñoz on 11/2/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import MapboxMaps
import shared

class PoiMapProxy: ObservableObject {
    
    var viewModel = PoiListViewModel(onNavigationEvent: { navigationEvent in
        print("Event: \(navigationEvent)")
    })
    
    @Published var state: PoiListState = PoiListState.InProgress()
    
    init() {
        viewModel.observe(viewModel.state) { newState in
            self.state = newState as! PoiListState
        }
    }
}

struct MapBoxMapView: UIViewControllerRepresentable {
    
    @ObservedObject var proxy = PoiListProxy { CoreNavigationEvent in
        print("Fixme")
    }
    
    func makeUIViewController(context: Context) -> MapViewController {
        proxy.viewModel.onEvent(event: PoiListEvent.Attach())
        return MapViewController()
    }
    
    func updateUIViewController(_ uiViewController: MapViewController, context: Context) {
        uiViewController.updateState(newState: proxy.state)
    }
    
}

class MapViewController: UIViewController {
    
    internal var mapView: MapView!
    
    override public func viewDidLoad() {
        super.viewDidLoad()
        
        
        let myResourceOptions = ResourceOptions(accessToken: "")
        let cameraOptions = CameraOptions(center: CLLocationCoordinate2D(latitude: 40.463667, longitude: -3.74922), zoom: 4.5, bearing: 0, pitch: 0)
        
        let myMapInitOptions = MapInitOptions(resourceOptions: myResourceOptions, cameraOptions: cameraOptions)
        
        mapView = MapView(frame: view.bounds, mapInitOptions: myMapInitOptions)
        mapView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.view.addSubview(mapView)
        
    }
    
    func updateState(newState: PoiListState){
        if newState is PoiListState.InProgress {
            print("In progress")
        } else if newState is PoiListState.Error {
            print("Error")
        } else if newState is PoiListState.Success {
            if let response = newState as? PoiListState.Success {
                print("Points \(response.pois)")
                showPoints(pois: response.pois)
            }
        }
    }
    
    func showPoints(pois: [Poi]){
        
        var locations : [PointAnnotation] = []
        for poi in pois {
            var pointAnnotation = PointAnnotation(coordinate: CLLocationCoordinate2D(latitude: poi.latitude, longitude: poi.longitude))
            
            pointAnnotation.image = .init(image: UIImage(systemName: "pin.fill")!, name: "pin.fill")
            pointAnnotation.iconAnchor = .bottom
            locations.append(pointAnnotation)
        }
        
        let pointAnnotationManager = mapView.annotations.makePointAnnotationManager()
        pointAnnotationManager.annotations = locations
    }
}

