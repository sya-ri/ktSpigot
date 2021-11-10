# Contributing

## Gradle タスク

### ビルド

```shell
./gradlew build

gradlew.bat build
```

全てのプロジェクトをビルドします。

### テスト

```shell
./gradlew test

gradlew.bat test
```

テストコードの確認をします。

### コードのフォーマット

```shell
./gradlew formatKotlin

gradlew.bat formatKotlin
```

ソースコードを整形します。コミットをする前に確認してください。

### 依存関係の更新確認

```shell
./gradlew dependencyUpdates

gradlew.bat dependencyUpdates
```

導入している依存関係に更新が存在するか確認します。

### コードスニペットの更新

```shell
./gradlew :showcase:updateCodeSnippet

gradlew.bat :showcase:updateCodeSnippet
```

`.md` のコードスニペットを更新します。事前に `wiki/` をクローンしてください。

```shell
git clone https://github.com/sya-ri/ktSpigot.wiki wiki
```
