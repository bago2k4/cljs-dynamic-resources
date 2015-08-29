(ns cljs-dynamic-deps.utils
  (:require [dommy.core :as dommy]))

(defn append! [new-node container]
  (dommy/append! container new-node))