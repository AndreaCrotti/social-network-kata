(ns social-kata.core)

(def timelines (atom {"natasha" ["hello world!"]
                      "andrea" ["this is not a tweet"]
                      "alex" ["does this work?"]}))

(def followers (atom {"andrea" ["alex" "natasha"]}))

(defn add-el
  [atom key val]
  (merge-with concat atom {key [val]}))


(defn post-tweet
  [username tweet]
  (swap! timelines add-el timelines username tweet)
  "success!")


(defn get-tweets
  [username]
  (@timelines username))


(defn subscribe
  [follower following]
  (swap! followers add-el followers following))
