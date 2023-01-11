//
//  Navigator.swift
//  iosApp
//
//  Created by Pilar García on 10/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

class Navigator: ObservableObject {
    // MARK: - Navigation properties
    @Published var doBack = false
    @Published var doNavigate = false
    @Published var doPushNavigation = false
    @Published var viewToNavigate: AnyView?
    
    func navigateToView(route: NavigationRoute) {
        switch route {
        case .Home:
            viewToNavigate = AnyView(HomeRoute())
            doNavigate = true
        }
    }
    
    func goBack(){
        doBack = true
    }
}

enum NavigationRoute {
    case Home
}

enum LandingRouteTab {
    case Home
    case GiftCards
    case History
}
