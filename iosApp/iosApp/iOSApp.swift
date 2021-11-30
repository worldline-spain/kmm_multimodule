import SwiftUI
import PoiUI

@main
struct iOSApp: App {
    
    init() {
        PoiUiModule().initiOS()
    }
    
	var body: some Scene {
		WindowGroup {
			PoiListView()
		}
	}
}
