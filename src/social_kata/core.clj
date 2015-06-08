(ns social-kata.core)

(def timelines (atom {}))

(defn add-tweet
  [a-timeline username tweet]
  (merge-with concat a-timeline {username [tweet]}))


(defn post
  [username tweet]
  (swap! timelines add-tweet username tweet))


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
