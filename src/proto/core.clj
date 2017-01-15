(ns proto.core)
(use 'org.httpkit.server
     '[clojure.string :only (split)]
     )

; before you use this, write some simple infix notation arithmetic
; in your environment as SOME_MATH. e.g export SOME_MATH='2 * 10'

(defmacro naive-infix []
  (let [[operand1 op operand2] (split (System/getenv "SOME_MATH") #"\s+")]
    `(~(symbol op) ~(read-string operand1)  ~(read-string operand2))))


(def body (str "look mom, it's infix notation from the environment! " (System/getenv "SOME_MATH") " is " (naive-infix)))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body body
   })

(run-server handler{:port 8080})


