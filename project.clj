(defproject social_kata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :ring {:handler social-kata.handler/app
         :auto-reload? true
         :auto-refresh? true}

  :plugins [[lein-ring "0.8.13"]]

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]])
