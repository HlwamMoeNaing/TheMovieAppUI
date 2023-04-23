package com.example.themovieappui.network.dataagent

//object MovieDataAgentImpl : MovieDataAgent {
//    override fun getNowPlayingMovies() {
//        GetTopRatedMovieTask().execute()
//    }
//
//    class GetTopRatedMovieTask : AsyncTask<Void, Void, MovieListResponse?>() {
//        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
//            val url: URL
//            var reader: BufferedReader? = null
//            val stringBuilder: StringBuilder
//            try {
//                url =
//                    URL("""${URLConstant.BASEURL}${URLConstant.API_GET_NOW_PLAYING}?api_key=${URLConstant.MOVIE_API_KEY}&language=en-US&page=1""")
//                val connection = url.openConnection() as HttpURLConnection
//                connection.requestMethod = "GET"
//
//                connection.readTimeout = 15 * 1000
//                connection.doInput = true
//                connection.doOutput = false
//                connection.connect()
//                reader = BufferedReader(InputStreamReader(connection.inputStream))
//                stringBuilder = StringBuilder()
//                for (line in reader.readLines()) {
//                    stringBuilder.append(line + "\n")
//                }
//                val responseString = stringBuilder.toString()
//
//                val movieListResponse = Gson().fromJson(
//                    responseString, MovieListResponse::class.java
//                )
//
//                return movieListResponse
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("NewsErroe", e.message ?: "")
//            } finally {
//                if (reader != null) {
//                    try {
//
//                        reader.close()
//                    } catch (ioe: IOException) {
//                        ioe.printStackTrace()
//                    }
//                }
//            }
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//
//}