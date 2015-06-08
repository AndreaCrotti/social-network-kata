(ns social-kata.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [social-kata.core :refer :all]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Welcome to the social network")
  (GET "/tweets" [username] (get-tweets username))
  (POST "/tweet" [username message] (post-tweet username message))
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
