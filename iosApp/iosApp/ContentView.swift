import SwiftUI
import poilistvm
import poi
import local
import remote
import core
import KMPNativeCoroutinesCombine


struct ContentView: View {

    let vm = PoiListViewModel(
        executor: Executor(),
        poiRepository: SharedPoiRepository(
            local: SharedPoiLocal(driver: DbDriverFactory()) as! LocalPoiLocal,
            remote: SharedPoiRemote() as! RemotePoiRemote
        ) as! PoiPoiRepository
    )
    
    
	var body: some View {
		Text("greet")
	}
}

struct ContentView_Previews: PreviewProvider {
    
	static var previews: some View {
		ContentView()
	}
}
