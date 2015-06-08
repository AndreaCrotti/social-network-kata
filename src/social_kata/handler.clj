(ns social-kata.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults site-defaults]]
            [social-kata.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [hiccup.core :refer [html]]))

(defn- emit-json
  [x & [status]]
  (let [json-data (json/json-str x)]
    {:headers {"Content-Type"   "application/json"
               "Vary"           "Accept-Encoding"
               "Content-Length" (str (.length json-data))}
     :status  (or status 200)
     :body    json-data}))



(defn show-users
  []
  (html
    [:html
     [:body
      [:h1 "Twit R"]
      [:h2 "Who do you want to follow?"]
      [:ul (map #(-> [:li [:a {:href (str "/tweets/" %)} %]])
                (get-users))]]]))

(defn show-tweets
  [username]
  (html
    [:html
     [:body
      [:h1 "Twit R"]
      [:h2 (str username " says:")]
      [:ul (map #(-> [:li %])
                (get-tweets username))]]]))


(defroutes app-routes
           (GET "/tweet" [] {:status  200
                        :headers {"Content-Type" "text/html"}
                        :body    (str "<html><body><h1>Twit R</h1><h2>What's happening?</h2><form action=\"/api/tweet\" method=\"post\">"
                                      "<p><label for=\"username\">Username</label><input id=\"username\" name=\"username\" type=\"text\">"
                                      "</input></p><p><label for=\"message\">Message</label><input id=\"message\" name=\"message\" type=\"text\">"
                                      "</input><input type=\"submit\" value=\"Post Message\"></input></p></form></body></html>")})

           (GET "/" [] {:status  200
                        :headers {"Content-Type" "text/html"}
                        :body    (show-users)})

           (GET "/tweets/:username"
                [username]
             {:status  200
              :headers {"Content-Type" "text/html"}
              :body    (show-tweets username)}
             )
           (GET "/api/tweets" [username] (emit-json (get-tweets username)))
           (POST "/api/tweet" [username message] (do (println "got" username message) (emit-json (post-tweet username message))))

           (POST "/subscribe" [follower following]
             (emit-json (subscribe follower following)))

           (GET "/feed" [username]
             (emit-json (get-feed username)))

           (route/not-found "Not found"))





(def app
  (wrap-defaults app-routes api-defaults))
