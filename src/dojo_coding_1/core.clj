(ns dojo-coding-1.core
  (:use [midje.sweet]))

(defn- graph-lines "Given a graph, create the graphviz dot lines in a seq. Add the nodes then the relations between them."
  [g]
  (let [ks (keys g)]
    (concat
     ["digraph fn {"]
     (map #(str "\"" % "\";") ks)
     (mapcat
      (fn [k]
        (map #(str "\"" k "\" -> \"" % "\";") (g k)))
      ks)
     ["}"])))

(fact
  (graph-lines {"node1" #{"node2"}
                "node2" #{"node1" "node3"}
                "node3" #{}}) => (just
                                  ["digraph fn {"
                                   "\"node1\";"
                                   "\"node2\";"
                                   "\"node3\";"
                                   "\"node1\" -> \"node2\";"
                                   "\"node2\" -> \"node1\";"
                                   "\"node2\" -> \"node3\";"
                                   "}"] :in-any-order))

m
(keys m)
(vals m)

(def m {:key0 [:key1]
        :key1 [:key2 :key3]
        :key2 []
        :key3 []})

(mapcat (fn [n]
          (for [v (m n)]
            (str n " -> " v)))
        (keys m))

(for [n (keys m)
      v (m n)]
  (str n " -> " v))



