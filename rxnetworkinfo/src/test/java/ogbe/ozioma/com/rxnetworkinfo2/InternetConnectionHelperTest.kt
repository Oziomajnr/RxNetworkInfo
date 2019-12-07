package ogbe.ozioma.com.rxnetworkinfo2

import android.net.ConnectivityManager
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class InternetConnectionHelperTest {

    @Test
    fun `connectivity manager creates a single instance`() {
        val firstInstance =
            InternetConnectionHelper.getInstance(ApplicationProvider.getApplicationContext())
        val secondInstance =
            InternetConnectionHelper.getInstance(ApplicationProvider.getApplicationContext())
        assertEquals(firstInstance, secondInstance)
    }
}