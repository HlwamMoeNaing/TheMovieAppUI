package com.example.themovieappui.network.dataagent

//object OkHttpDataAgentImpl : MovieDataAgent {
//    private var mClient = OkHttpClient.Builder()
//        .connectTimeout(15, TimeUnit.SECONDS)
//        .readTimeout(15, TimeUnit.SECONDS)
//        .writeTimeout(15, TimeUnit.SECONDS)
//        .build()
//
//    override fun getNowPlayingMovies() {
//        GetNowPlayingOkhttpTask(mClient).execute()
//    }
//
//    class GetNowPlayingOkhttpTask(
//        private var mOkHttpClient: OkHttpClient
//    ) : AsyncTask<Void, Void, MovieListResponse?>() {
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val request = Request.Builder()
//                .url("""${URLConstant.BASEURL}${URLConstant.API_GET_NOW_PLAYING}?api_key=${URLConstant.MOVIE_API_KEY}&language=en-US&page=1""")
//                .build()
//            try {
//                val response = mOkHttpClient.newCall(request).execute()
//                if (response.isSuccessful) {
//                    Log.d("@okHttp","Success " +response.body.toString())
//                    response.body?.let {
//                        val responseString = it.string()
//                        val response = Gson().fromJson(
//                            responseString, MovieListResponse::class.java
//                        )
//                        return response
//                    }
//                }
//            } catch (e: Exception) {
//                Log.d("@okHttp","Fail "+e.message.toString())
//                e.printStackTrace()
//            }
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//}