package co.za.kaylentravispillay.skelly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.za.kaylentravispillay.skelly.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialiseLoadingSkeleton()
        binding.loadingSkeleton.enableShimmer(true)
    }

    private fun initialiseLoadingSkeleton() {
        val dimen100 = resources.getDimensionPixelSize(R.dimen.dimen_100)
        val dimen19 = resources.getDimensionPixelSize(R.dimen.dimen_19)
        val dimen8 = resources.getDimensionPixelSize(R.dimen.dimen_8)
        val dimen16 = dimen8 * 2

        binding.loadingSkeleton.buildSkelly {
            horizontalBoneStack {
                bone {
                    width { dimen100 }
                    height { dimen100 }
                }

                verticalBoneStack {
                    marginStart { dimen16 }

                    bone {
                        height { dimen19 }
                    }

                    horizontalBoneStack {
                        height { dimen19 }
                        marginTop { dimen8 }

                        bone {
                            backgroundColorRes { R.color.teal_700 }
                            width { dimen19 }
                        }

                        bone {
                            marginStart { dimen8 }
                        }
                    }

                    bone {
                        marginTop { dimen8 }
                        height { dimen19 }
                    }

                    bone {
                        marginTop { dimen8 }
                        height { dimen19 }
                    }
                }
            }

            bone {
                marginTop { dimen16 }
                height { dimen100 }
            }
        }
    }
}