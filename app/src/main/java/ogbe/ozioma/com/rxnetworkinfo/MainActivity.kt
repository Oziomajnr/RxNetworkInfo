package ogbe.ozioma.com.rxnetworkinfo

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ogbe.ozioma.com.rxnetworkinfo2.InternetConnectionHelper

class MainActivity : AppCompatActivity() {


    private val internetConnectionHelper: InternetConnectionHelper by lazy {
        InternetConnectionHelper.Companion.getInstance(this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }

    val disposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposable.add(internetConnectionHelper.internetConnectionAvailable.observeOn(
            AndroidSchedulers.mainThread()
        )
            .subscribe {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            })
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
