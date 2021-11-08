import SwiftUI

import poilistvm

@main
struct iOSApp: App {
    
    init() {
        PoiUiModule().initiOS()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
