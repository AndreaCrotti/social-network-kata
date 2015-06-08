(ns social-kata.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [social-kata.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]))

(defn- emit-json
  [x & [status]]
  (let [json-data (json/json-str x)]
    {:headers {"Content-Type" "application/json"
               "Vary" "Accept-Encoding"
               "Content-Length" (str (.length json-data))}
     :status (or status 200)
     :body json-data}))

(defroutes app-routes
  (GET "/" [] "Welcome to the social network")
  (GET "/tweets" [username] (emit-json (get-tweets username)))
  (POST "/tweet" [username message] (emit-json (post-tweet username message)))
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
