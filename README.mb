retry-handler
=============

特定の処理を実行したいんだけど、途中で何らかのエラーが発生した場合はリトライさせたい時に使えます。


具体的にはこんな感じで書くと、処理の途中でエラーが発生しても指定した回数はリトライしてくれます。

 Proc.retry(3,new Runnable() {

     @Override
     public void run() {
         //なんか処理
     }
 });


特定のエラーの時だけリトライしたい時はそれも指定できます。

例えば IOException とそのサブクラスのエラーの時のみリトライさせたい場合はこんな感じ

 Proc.retry(3,new Runnable() {
 
     @Override
     public void run() {
         //なんか処理
     }
 }, IOException.class);


処理をリトライするときに wait を入れることもできます。

例えば 5 秒置いてからリトライさせたい場合はこんな感じ

 Proc.retry(3,new Runnable() {
 
     @Override
     public void run() {
         //なんか処理
     }
 }, 5 * 1000);


もちろん、上記を複合で指定することも可能です。

例えば IOException とそのサブクラスのエラーの時のみ 5 秒置いてからリトライさせたい場合はこんな感じ

 Proc.retry(3,new Runnable() {

     @Override
     public void run() {
         //なんか処理
     }
 }, 5 * 1000, IOException.class);

とりあえず使い方はテスト見ればわかると思うので適当にどうぞ

[![Build Status](https://secure.travis-ci.org/yoshiori/retry-handler.png?branch=master)](http://travis-ci.org/yoshiori/retry-handler)