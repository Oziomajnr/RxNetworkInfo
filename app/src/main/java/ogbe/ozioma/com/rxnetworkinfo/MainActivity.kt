package ogbe.ozioma.com.rxnetworkinfo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ogbe.ozioma.com.rxnetworkinfo2.InternetConnectionHelper

class MainActivity : AppCompatActivity() {


    private val internetConnectionHelper: InternetConnectionHelper by lazy {
        InternetConnectionHelper.Companion.getInstance(this)
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
