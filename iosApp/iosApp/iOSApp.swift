import SwiftUI
import PoiUI
import HomeUI

@main
struct iOSApp: App {
    
    init() {
        HomeUiModule().initiOS()
        PoiUiModule().initiOS()
    }
    
	var body: some Scene {
		WindowGroup {
            PoiListView()
		}
	}
}
