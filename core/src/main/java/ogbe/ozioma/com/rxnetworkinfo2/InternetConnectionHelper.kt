package ogbe.ozioma.com.rxnetworkinfo2

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class InternetConnectionHelper private constructor(private val connectivityManager: ConnectivityManager) {

    companion object : SingletonHolder<InternetConnectionHelper, ConnectivityManager>(::InternetConnectionHelper)

    private val newtWorkRequest: NetworkRequest = NetworkRequest.Builder().build()

    val internetConnectionAvailable = createNetworkStateFlowable()

    private fun createNetworkStateFlowable(): Flowable<Boolean> {
        return Flowable.create<Boolean>({ emitter ->
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network?) {
                    //record wi-fi disconnect event
                    if (!emitter.isCancelled) {
                        emitter.onNext(false)
                    }
                }

                override fun onUnavailable() {
                    //record wi-fi disconnect event
                    if (!emitter.isCancelled) {
                        emitter.onNext(false)
                    }
                }

                override fun onAvailable(network: Network?) {
                    //record wi-fi connect event
                    //record wi-fi disconnect event
                    if (!emitter.isCancelled) {
                        emitter.onNext(true)
                    }
                }

            }

            connectivityManager.registerNetworkCallback(newtWorkRequest, networkCallback)
            emitter.setCancellable {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                connectivityManager.requestNetwork(newtWorkRequest, networkCallback, 2000)
            } else {
                val isConnected = connectivityManager.activeNetworkInfo?.isConnected
                if (isConnected == true && !emitter.isCancelled) {
                    emitter.onNext(true)
                } else {
                    emitter.onNext(false)
                }
            }

        }, BackpressureStrategy.LATEST)
    }

}