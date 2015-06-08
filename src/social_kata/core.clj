(ns social-kata.core)

(def timelines (atom {"natasha" ["hello world!"]
                      "andrea" ["this is not a tweet"]
                      "alex" ["does this work?"]}))

(defn add-tweet
  [a-timeline username tweet]
  (merge-with concat a-timeline {username [tweet]}))


(defn post-tweet
  [username tweet]
  (swap! timelines add-tweet username tweet)
  "success!")

(defn get-tweets
  [username]
  (@timelines username))

