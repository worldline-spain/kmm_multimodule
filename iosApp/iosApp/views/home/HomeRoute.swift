//
//  HomeRoute.swift
//  iosApp
//
//  Created by Sergio Casero Hernández on 20/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import Combine

struct HomeRoute: View {
    
    @ObservedObject var viewModel : HomeViewModel = HomeViewModel().attach()
    @State var navigator : Navigator = Navigator()
    
    var onNavigationEvent: (NavigationEvent) -> Void

    var body: some View {
        HomeContent(
            state: viewModel.state(\.state, equals: { $0 === $1 }, mapper: { $0 }),
            onEvent: { event in
            viewModel.onEvent(event: event)
        }, onNavigationEvent: onNavigationEvent)
            .onAppear(perform: {
                viewModel.onEvent(event: HomeEvent.Attach())
            }).onDisappear(perform: {
                viewModel.detach()
            }).environmentObject(navigator)

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
