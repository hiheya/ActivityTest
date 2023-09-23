package work.icu007.activitytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import work.icu007.activitytest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindings = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        var extraData = intent.getStringExtra("extra_data")
//        extraData = extraData ?: "null"
        Toast.makeText(this, extraData ?: "null", Toast.LENGTH_SHORT).show()
        bindings.button2.text = "前往FirstActivity"
        bindings.button2.setOnClickListener{
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }
        bindings.button1.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(1024, intent)
            finish()
        }
    }
}