package work.icu007.activitytest


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import work.icu007.activitytest.databinding.FirstLayoutBinding

class FirstActivity : AppCompatActivity() {
    private val TAG: String? = "FirstActivity"
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val launcherCallback = ActivityResultCallback<ActivityResult> { result ->
        val code = result.resultCode
        val data = result.data
        Log.d(TAG, "launcherCallback: ==========start==========")
        Toast.makeText(this, "code: $code, data: ${data?.getStringExtra("data_return")}", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "launcherCallback: code: $code, data: ${data?.getStringExtra("data_return")}")
        Log.d(TAG, "launcherCallback: ========== end ==========")
    }

    companion object {
        private const val REQUEST_CODE = 1024
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            launcherCallback
        )

//        val button1: Button = findViewById(R.id.button1)
        binding.button1.text = "显式INTENT"
        binding.button1.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            val data = "hello SecondActivity"
            intent.putExtra("extra_data",data)
            startActivity(intent)
            Toast.makeText(this, "hello , u clicked button1: ${binding.button1.text}", Toast.LENGTH_SHORT).show()
        }
        binding.button2.text = "隐式INTENT"
        binding.button2.setOnClickListener {
            val intent1 = Intent(Intent.ACTION_DIAL)
            intent1.data = Uri.parse("tel:10086")
//            val intent = Intent("com.icu007.activitytest.ACTION_START")
//            intent.addCategory("com.icu007.activitytest.MY_CATEGORY")
            startActivity(intent1)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
//            startActivityForResult(intent, REQUEST_CODE)
            resultLauncher.launch(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            REQUEST_CODE ->{
                val code = requestCode
                val data = data?.getStringExtra("data_return")
                Toast.makeText(this, "code: $code, data: $data", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onActivityResult: code: $code, data: $data")
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add_item -> Toast.makeText(this, "u clicked add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "u clicked remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}