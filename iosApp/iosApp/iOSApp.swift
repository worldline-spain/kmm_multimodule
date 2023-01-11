import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        IosDIKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            HomeRoute()
        }
    }
}
