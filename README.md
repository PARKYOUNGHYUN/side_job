# 強み売りサービス（バッグエンド側）
副業・兼業を希望する者に対するマッチングサービス
1. 登録者は自分の知識・スキル・レベルをアプリに登録する。
2. 受け手は自分の興味がある分野の人を選択し、申し込む。
3. お互いの条件がマッチした場合に取引成立！
4. 日時を決めてカフェなどでちょっとした楽しい習い事！

## 得意部分
Spring BootとJPAを利用して、restAPIを実装した。
下記のフォルダ内のファイルを利用して、restAPI機能を提供している。（各フォルダ内のソースコードはがにている）

・リクエストに対する処理とレスポンス出力
```
side_job\restapi\src\main\java\com\restapi\controller
```

・データベースのエンティティ
```
side_job\restapi\src\main\java\com\restapi\entity
```

・データベースとの連携
```
side_job\restapi\src\main\java\com\restapi\service
```

## 工夫点
Spring Bootの開発に関しては分かっていたので、データベースの設計や、WebAPI設計に合わせて
どのように合わせるかに対して、念を入れた。

## 苦手だったこと
サービスに関してただの考えだけだったが、
想像しただけだったのを機能分析や、データベースで定義するのがすごく難しかった。

## 改善したいこと
テーブル定義に関して、すごく苦手だと感じたので、
次回からはDBに関する勉強を通じて、データモデリングからはっきり進みたい。

## 開発構築
### ツール準備
1. visual studio codeダウンロード
2. プラグインをインストール(Java Extension Pack, Spring Boot Extension Pack, Lombok Annotations Support for VS Code)

###  Git　リポジトリの取得
```
git clone https://github.com/PARKYOUNGHYUN/side_job.git
```

### 実行
3. F5押してSpring Bootアプリケーションが起動する