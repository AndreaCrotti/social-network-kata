(ns social-kata.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] "Welcome to social the network")
  (route/not-found "Not found"))

(def app
  (wrap-defaults app-routes api-defaults))
