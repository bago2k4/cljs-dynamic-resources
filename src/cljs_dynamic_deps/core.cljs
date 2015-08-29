(ns cljs-dynamic-deps.core
  (:require [cljs-dynamic-deps.utils :refer [append!]]
            [dommy.core :as dommy :refer-macros [sel sel1]]))

(enable-console-print!)

(def ^:private script-default-opts
  {:container-sel :body
   :type "text/javascript"})

; <script src="src" type=":type" onload="cb"></script>
(defn add-script 
  "Append a script element"
  ([src cb opts]
    (println "Inside add-script yeah!!! ahahahha")
    (let [opts (merge opts script-default-opts) ; normalize the options map
          parent-node (sel1 (:container-sel opts))] ; get the container node
      (println "Parent element: " parent-node)
      (-> ; create a script element
          (dommy/create-element :script)
          ; set the type attribute
          (dommy/set-attr! :type (:type opts))
          ; set src attribute
          (dommy/set-attr! :src src)
          ; attach the on load event
          (dommy/listen! :load cb)
          ; append the newly created element to the parent node
          (append! parent-node))))
  ([src cb]
   (add-script src cb script-default-opts))
  ([src]
   (add-script src #())))

(def ^:private style-default-opts
  {:container-sel :head
   :rel "stylesheet"})

; <style href="link-to-style" rel=":rel" onload="cb"></script>
(defn add-style 
  "Append a style element"
  ([src cb opts]
    (when (not (nil? src))
      (println "Inside add-style yeah!!!")
      (let [opts (merge opts style-default-opts)
            parent-node (sel1 (:container-sel opts))]
        (println "Parent element: " parent-node)
        (-> ; create a script element
            (dommy/create-element :style)
            ; set the rel attribute
            (dommy/set-attr! :rel (:rel opts))
            ; set the href attribute
            (dommy/set-attr! :href src)
            ; attach the on load event
            (dommy/listen! :load cb)
            ; append the newly created element to the parent node
            (append! parent-node)))))
  ([src cb]
   (add-style src cb style-default-opts))
  ([src]
   (add-style src nil)))
