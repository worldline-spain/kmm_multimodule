//
//  HomeRoute.swift
//  iosApp
//
//  Created by Sergio Casero Hernández on 20/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import HomeUI
import Combine
import core

class HomeProxy: ObservableObject {
    var viewModel = HomeViewModel()
    
    @Published var state: HomeState = HomeState.List()
    
    init() {
        viewModel.observe(viewModel.state) { newState in
             self.state = newState as! HomeState
        }
    }
}

struct HomeRoute: View {
    
    @ObservedObject var proxy = HomeProxy()
    
    var onNavigationEvent: (NavigationEvent) -> Void

    var body: some View {
        HomeContent(state: proxy.state, onEvent: { event in
            proxy.viewModel.onEvent(event: event)
        }, onNavigationEvent: onNavigationEvent)
            .onAppear(perform: {
                proxy.viewModel.onEvent(event: HomeEvent.Attach())
            }).onDisappear(perform: {
                proxy.viewModel.detach()
            })

    }
}

struct HomeContent: View {
    
    var state: HomeState
    var onEvent: (HomeEvent) -> Void

    var onNavigationEvent: (NavigationEvent) -> Void
    
    @State private var selection: Int = 1
    
    var body: some View {
        VStack {
            TabView(selection: $selection){
                TabContainer(state: state, onNavigationEvent: onNavigationEvent)
                    .tabItem(){
                        Image(systemName: "list.bullet")
                        Text("List")
                    }.tag(1)
                TabContainer(state: state, onNavigationEvent: onNavigationEvent)
                    .tabItem(){
                        Image(systemName: "map")
                        Text("Map")
                    }.tag(2)
            }
            .onReceive(Just(selection)) {
                if $0 == 1 {
                    onEvent(HomeEvent.List())
                } else if $0 == 2 {
                    onEvent(HomeEvent.Map())
                }
            }
        }
    }
}


struct TabContainer: View {
    
    var state: HomeState
    
    var onNavigationEvent: (NavigationEvent) -> Void
    
    var body: some View {
        if state is HomeState.List {
            PoiListView { coreNavigationEvent in
                onNavigationEvent(NavigationEvent.Detail(id: 1))    // Fixme
            }
        } else {
            MapBoxMapView()
        }
    }
}
