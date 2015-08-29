(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'cljs-dynamic-deps.core
   :output-to "out/cljs_dynamic_deps.js"
   :output-dir "out"})
