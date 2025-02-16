package com.example.myapplication.ui

//import com.bumptech.glide.Glide
//import com.bumptech.glide.Glide
//import com.moengage.inbox.ui.MoEInboxUiHelper
//import com.moengage.inbox.ui.view.InboxActivity
//import com.moengage.inbox.ui.view.InboxActivity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.util.Util
import com.example.myapplication.R
import com.example.myapplication.Utils
import com.example.myapplication.articlesViewModel.ArticlesViewModel
import com.example.myapplication.databinding.FragmentArticlesBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.gson.Gson
import com.moengage.core.MoECoreHelper
import com.moengage.core.Properties
import com.moengage.core.analytics.MoEAnalyticsHelper
import com.moengage.core.enableAdIdTracking
import com.moengage.core.listeners.UserDeletionListener
import com.moengage.core.model.user.deletion.UserDeletionData
import com.moengage.inapp.MoEInAppHelper
import com.moengage.inapp.listeners.InAppLifeCycleListener
import com.moengage.inapp.listeners.OnClickActionListener
import com.moengage.inapp.listeners.SelfHandledAvailableListener
import com.moengage.inapp.listeners.SelfHandledCampaignsAvailableListener
import com.moengage.inapp.model.ClickData
import com.moengage.inapp.model.InAppData
import com.moengage.inapp.model.SelfHandledCampaignData
import com.moengage.inapp.model.SelfHandledCampaignsData
import com.moengage.inapp.model.actions.CustomAction
import com.moengage.pushbase.MoEPushHelper


class ArticlesFragment : Fragment() {
    private lateinit var binding: FragmentArticlesBinding
    private lateinit var newsAdapter: NewsAdapter
    private val articlesViewModel: ArticlesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticlesBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(36)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        MyApplication.moEngage


        enableAdIdTracking(requireActivity())

//        MoEGeofenceHelper.getInstance().startGeofenceMonitoring(requireActivity())

        binding.openCardActivityBtn.setOnClickListener {
            MoECoreHelper.logoutUser(requireActivity())

        }

        binding.openMyCardActivityBtn.setOnClickListener {
//            val i = Intent(requireActivity(), MyCustomCategoryActivity::class.java)
//            requireActivity().startActivity(i)
        }

        binding.openCustomWebViewActivityBtn.setOnClickListener {
//            val i = Intent(requireActivity(), CustomWebView::class.java)
//            requireActivity().startActivity(i)

//            disableSdk(requireActivity())
        }

//
//                    val property = Properties().addAttribute("quantity", 2)
//                // tracking string
//                .addAttribute("product", "iPhone")
//                // tracking date
//                .addAttribute("purchaseDate", Date())
//                // tracking double
//                .addAttribute("price", 5999.99)
//                // tracking location
//                .addAttribute("userLocation", GeoLocation(40.77, 73.98))
//
//            MoEAnalyticsHelper.trackEvent(requireActivity(), "Purchase", property)

        binding.trackPurchaseEvent.setOnClickListener {
//            requireActivity().startActivity(Intent(requireActivity(), TestActivity::class.java))

//            val property = Properties().addAttribute("raw", "John cena")
//
//            MoEAnalyticsHelper.trackEvent(requireActivity(), "WWE RAW", property)

            MoECoreHelper.deleteUser(requireActivity(),object : UserDeletionListener {
                override fun onResult(data: UserDeletionData) {
                    Log.d(Utils.MOENGAGE_TAG, "onResult of user deletion data: $data")
                }

            })
        }



        binding.defInboxBtn.setOnClickListener {
//            MoEInboxUiHelper.getInstance().setInboxAdapter(DefaultCustomInboxAdapter())
//            startActivity(Intent(requireActivity(), InboxActivity::class.java))
        }

        binding.openInboxActivityBtn.setOnClickListener {
//            startActivity(Intent(requireActivity(), CustomInboxActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
//            startActivity(Intent(requireActivity(), InboxActivity::class.java))
        }

        MoEAnalyticsHelper.setEmailId(requireActivity(), "altiora.kia@moengage.com")

//        if (ContextCompat.checkSelfPermission(
//                requireActivity(), Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
////            Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_SHORT).show()
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                requestPostPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//            } else {
//                Toast.makeText(
//                    requireContext(),
//                    "Permission already granted below android level 13",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }

        MoEPushHelper.getInstance().requestPushPermission(requireActivity())


        binding.apply {
            newsAdapter = NewsAdapter {
                val properties = Properties()
                properties.addAttribute("title", it.title)
                properties.addAttribute("source", it.source.name)
                properties.addAttribute("publishedDate", it.publishedAt)

                MoEAnalyticsHelper.trackEvent(requireActivity(), "Tap on News", properties)
//                val action =
//                    ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(it.url)
//                findNavController().navigate(action)
            }

            this.mtToolbar.toolbar.apply {
                title = requireActivity().getString(R.string.newsapp)
                this.inflateMenu(R.menu.sort_news_menu)
                this.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_oldest_news -> {
                            newsAdapter.getOldestNews()
                            true
                        }

                        R.id.action_latest_news -> {
                            newsAdapter.getLatestNews()
                            true
                        }

                        else -> false
                    }
                }
            }

            this.swipeRefreshContainer.setOnRefreshListener(swipeRefreshListener)

            this.newsRv.apply {
                this.layoutManager = LinearLayoutManager(requireActivity())
                this.adapter = newsAdapter
            }


            this.sendF1Btn.setOnClickListener {
//                findNavController().navigate(R.id.action_articlesFragment_to_f1)
//                startActivity(Intent(requireActivity(), CardActivity::class.java))

                val currentAndroidVersion = Build.VERSION.SDK_INT

//                println("Current Android version: $currentAndroidVersion")

                Log.d("sendf1", "current version: $currentAndroidVersion")

            }

            this.sendF2Btn.setOnClickListener {
//                findNavController().navigate(R.id.action_articlesFragment_to_f2)
                val i = Intent(requireActivity(), MyCustomCategoryActivity::class.java)
                requireActivity().startActivity(i)
            }


            this.sendF3Btn.setOnClickListener {
//                findNavController().navigate(R.id.action_articlesFragment_to_f32)
                MoEAnalyticsHelper.setUniqueId(requireActivity(), "Johnathan")

            }

            this.showInAppBtn.setOnClickListener {

                MoEInAppHelper.getInstance()
                    .getSelfHandledInApp(requireActivity(), object : SelfHandledAvailableListener {
                        override fun onSelfHandledAvailable(data: SelfHandledCampaignData?) {
                            val myPayload = data?.campaign?.payload
                            Log.d(Utils.MOENGAGE_TAG, "mypnshav: $myPayload")

//                    val myData: SelfHandledCampaignData = showData(data!!)
//                    MoEInAppHelper.getInstance().selfHandledShown(requireActivity(), myData)


                        }

                    })

            }


            this.sendNotificationBtn.setOnClickListener {

//                disableSdk(requireActivity())
//                FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        Log.w(
//                            "ArticlesFrg", "Fetching FCM registration token failed", task.exception
//                        )
//                        return@addOnCompleteListener
//                    }
//
//                    val token = task.result
//                    Log.d("ArticlesFrg", "Resultant token: $token")
//                    Toast.makeText(requireActivity(), "Resultant token: $token", Toast.LENGTH_SHORT)
//                        .show()
//
//                }

//                MoEFireBaseHelper.getInstance().addTokenListener(object : TokenAvailableListener {
//                    override fun onTokenAvailable(token: Token) {
//                        val myToken = token.pushToken
//                        Log.d("ArticlesFrg", "push token: $myToken ")
//                    }
//
//                })

            }

            if (checkGooglePlayServicesAvailability()) {

            } else {
                Log.w("articleFrg", "Device doesn't have google play services")
            }
        }


        articlesViewModel.newsResponse.observe(viewLifecycleOwner) { result ->

            if (result?.articles != null) {
                newsAdapter.updateNews(result.articles)
                binding.newsRv.visibility = View.VISIBLE
            } else {
                binding.newsRv.visibility = View.GONE
                binding.noInternet.root.visibility = View.VISIBLE
            }

        }


//        articlesViewModel.getAllArticles()

    }


    private val requestPostPermissionLauncher = registerForActivityResult(
        RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            MoEPushHelper.getInstance().pushPermissionResponse(requireActivity(), isGranted)
//            Toast.makeText(
//                requireActivity(), "Grant permission above api level 33", Toast.LENGTH_SHORT
//            ).show()
        } else {
//            Toast.makeText(requireActivity(), "Permission cannot be granted", Toast.LENGTH_SHORT)
        }
    }

    private val swipeRefreshListener = OnRefreshListener {
        binding.swipeRefreshContainer.isRefreshing = true
        articlesViewModel.getAllArticles()
    }

    private fun checkGooglePlayServicesAvailability(): Boolean {
        val status =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(requireActivity())
        return if (status != ConnectionResult.SUCCESS) {
            Log.e("articleFrg", "Error")
            false
        } else {
            Log.i("articleFrg", "Google play services updated")
            true
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("start", "on start called: ")

//        Toast.makeText(requireActivity(), "art frg start", Toast.LENGTH_LONG).show()
//        MoEInAppHelper.getInstance().showInApp(requireActivity())

//        MoEInAppHelper.getInstance().setInAppContext(setOf("home"))
//        MoEInAppHelper.getInstance().showNudge(requireActivity())

//        MoEInAppHelper.getInstance().addInAppLifeCycleListener(object : InAppLifeCycleListener {
//            override fun onDismiss(inAppData: InAppData) {
//                // has to be implemented
//                Log.d(articlesFrgTag, "on dismiss is invoked on start ")
//                Log.d(articlesFrgTag, "in app data: $inAppData")
//            }
//
//            override fun onShown(inAppData: InAppData) {
//                Log.d(articlesFrgTag, "on shown is invoked on start ")
//            }
//
//        })
    }

    override fun onPause() {
        super.onPause()
//        Toast.makeText(requireActivity(), "art frg pause", Toast.LENGTH_LONG).show()
        Log.d("pause", "on pause called: ")
//        MoEInAppHelper.getInstance().resetInAppContext()


    }


    fun showData(data: SelfHandledCampaignData?): SelfHandledCampaignData {

        val myPayLoad = data?.campaign?.payload
        Log.d(Utils.MOENGAGE_TAG, "showData: $myPayLoad")

//        Log.d(Utils.MOENGAGE_TAG, "showDataWithContext: ${data.campaignData}")


        val secondMessagePayloadResponse = Gson().fromJson(myPayLoad, SecondMessage::class.java)
        Log.d(Utils.MOENGAGE_TAG, "my second payload response : $secondMessagePayloadResponse")
        val title = secondMessagePayloadResponse.template_id
        Log.d(Utils.MOENGAGE_TAG, "show dialog title: $title")
        val myAlertDialog = AlertDialog.Builder(requireActivity()).create()


        val myCustomView = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        myCustomView.findViewById<TextView?>(R.id.title).apply {
            text = title
        }

        val inAppImage = myCustomView.findViewById<ImageView?>(R.id.inApp_iv)

//        Glide.with(requireActivity()).load(secondMessagePayloadResponse.image_url).into(inAppImage)

        myAlertDialog.setView(myCustomView)


        myAlertDialog.setCancelable(false)

        val crossBtn: TextView = myCustomView.findViewById(R.id.cross_btn)

        crossBtn.setOnClickListener {
            MoEInAppHelper.getInstance().selfHandledDismissed(requireActivity(), data!!)
            myAlertDialog.dismiss()
        }
        myAlertDialog.show()
        return data!!

    }

    override fun onResume() {
        super.onResume()


//        MoEInAppHelper.getInstance().getSelfHandledInApps(requireActivity(),object : SelfHandledCampaignsAvailableListener{
//            override fun onCampaignsAvailable(campaigns: SelfHandledCampaignsData?) {
//                Log.d(Utils.MOENGAGE_TAG, "multiple SDK camps list size: ${campaigns!!.campaigns}")
//            }
//
//        })
        MoEInAppHelper.getInstance().showInApp(requireActivity())
//        MoEInAppHelper.getInstance().showNudge(requireActivity())

//        MoECardHelper.setSyncCompleteListener(object : SyncCompleteListener {
//            override fun onSyncComplete(data: SyncCompleteData?) {
//
//                Toast.makeText(
//                    requireActivity(), "sync type resume : ${data?.syncType}", Toast.LENGTH_LONG
//                ).show()
//
//            }
//
//        })

//        Toast.makeText(requireActivity(), "art frg resume", Toast.LENGTH_LONG).show()

//        MoEInAppHelper.getInstance().showInApp(requireActivity())


//        MoEInAppHelper.getInstance().showNudge(requireActivity())

//        MoEInAppHelper.getInstance().setInAppContext(setOf("abc"))
//        MoEInAppHelper.getInstance()
//            .getSelfHandledInApp(requireActivity(), object : SelfHandledAvailableListener {
//                override fun onSelfHandledAvailable(data: SelfHandledCampaignData?) {
//                    val myPayload = data?.campaign?.payload
//                    Log.d(Utils.MOENGAGE_TAG, "mypnshav: $myPayload")
//
//                    val myData: SelfHandledCampaignData = showData(data!!)
//                    MoEInAppHelper.getInstance().selfHandledShown(requireActivity(), myData)
//
//
//                }
//
//            })


//        MoEInAppHelper.getInstance().setSelfHandledListener(object : SelfHandledAvailableListener {
//            override fun onSelfHandledAvailable(data: SelfHandledCampaignData?) {
//                val myPayload = data?.campaign?.payload
//                Log.d(Utils.MOENGAGE_TAG, "onSelfHandledAvailable event: $myPayload")
//                val myData: SelfHandledCampaignData = showData(data!!)
//                MoEInAppHelper.getInstance().selfHandledShown(requireActivity(), myData)
//            }
//
//        })

//        MoEInAppHelper.getInstance().showNudge(requireActivity())
//        MoEInAppHelper.getInstance().addInAppLifeCycleListener(object : InAppLifeCycleListener {
//            override fun onDismiss(inAppData: InAppData) {
//                // has to be implemented
//                Log.d(Utils.MOENGAGE_TAG, "on dismiss is invoked on resume ")
//                Log.d(Utils.MOENGAGE_TAG, "in app nudge data: $inAppData")
//
//            }
//
//            override fun onShown(inAppData: InAppData) {
//                Log.d(Utils.MOENGAGE_TAG, "on shown is invoked on resume ")
//                Log.d(Utils.MOENGAGE_TAG, "in app nudge data: $inAppData")
//            }
//
//        })

//        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
//            override fun onClick(clickData: ClickData): Boolean {
//                Log.d("Utils.MOENGAGE_TAG", "onClick in-app: $clickData ")
//
//
//                val kvPair = (clickData.action as CustomAction).keyValuePairs
//                Log.d(Utils.MOENGAGE_TAG, "kv pairs for custom action : $kvPair")
//                val i = Intent(Intent.ACTION_VIEW)
//                i.setData(Uri.parse(navigationUrl))
//                startActivity(i)
//                return false
//            }
//
//        })

//        MoEInAppHelper.getInstance().setClickActionListener(object : OnClickActionListener {
//            override fun onClick(clickData: ClickData): Boolean {
//                // handle click may be custom or navigation
//
//
//                val actionType = (clickData.action as NavigationAction).actionType
//                val navigationType = (clickData.action as NavigationAction).navigationType
//                val navigationUrl = (clickData.action as NavigationAction).navigationUrl
//
//                return if (cd.navigationType.name.equals(
//                        NavigationType.DEEP_LINKING
//                    )
//                ) {
//                    Log.d(articlesFrgTag, "my click data on navigate dep link: ${clickData.action} ")
//                    true
//                } else if (cd.navigationType.name.equals(NavigationType.RICH_LANDING)) {
//                    Log.d(articlesFrgTag, "my click data rich landing: ${clickData.action} ")
//                    true
//                } else{
//                    false
//                }
//
//                return if (actionType.name.equals(ActionType.NAVIGATE) /*&& navigationType.name.equals(
//                        NavigationType.DEEP_LINKING
//                    )*/
//                ) {
//                    Log.d(
//                        articlesFrgTag, "my click data on navigate deep link: ${clickData.action} "
//                    )
//                    val intent = Intent(Intent.ACTION_DEFAULT, Uri.parse(navigationUrl)).apply {
//                        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
//                    }
//
//                    requireActivity().startActivity(intent)
//
//                    true
//                } else {
//                    Log.d(articlesFrgTag, "default click: ${clickData.action} ")
//                    true
//                }
//
//            }
//
//        })
    }


    companion object {
        val articlesFrgTag = "ARTICLES_FRAGMENT"

    }

    override fun onStop() {
        super.onStop()

        Log.d("stop", "on stop called: ")

//        MoEInAppHelper.getInstance().resetInAppContext()
    }

//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        MoEInAppHelper.getInstance().onConfigurationChanged()
//    }

}