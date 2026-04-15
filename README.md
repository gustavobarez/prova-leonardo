# Estoque Virtual - Android (Java, API 24)

Aplicativo Android para cadastro e listagem de produtos de uma loja virtual.

O projeto foi desenvolvido em **Java** com **Room Database** para armazenamento local, contendo:

- Tela de cadastro de produto
- Tela de listagem de produtos
- Validações de campos obrigatórias
- Navegação entre telas

## Funcionalidades

### Cadastro de produto

Campos:

- Nome do produto
- Código do produto (alfanumérico)
- Preço (em reais)
- Quantidade em estoque

Validações implementadas:

- Nenhum campo pode ficar em branco
- Código aceita apenas letras e números
- Preço aceita apenas número positivo com até 2 casas decimais
- Quantidade aceita apenas número inteiro positivo

### Persistência local (Room)

- Entidade: `Product`
- DAO: `ProductDao`
- Banco: `ProductDatabase`

### Listagem de produtos

Exibe:

- Nome
- Código
- Preço

Também possui botão para voltar à tela de cadastro.

## Pré-requisitos

Antes de rodar, você precisa ter instalado:

1. **Android Studio** (versão recente)
2. **Android SDK** com plataforma compatível (projeto usa `compileSdk 36` e `minSdk 24`)
3. **JDK 11+** (ou JBR do Android Studio)
4. **Gradle Wrapper** (já incluído no projeto)

## Como rodar no Android Studio

1. Abra o Android Studio.
2. Selecione **Open** e escolha a pasta do projeto:
   - `provaleonardo`
3. Aguarde o sync do Gradle finalizar.
4. Inicie um emulador Android ou conecte um dispositivo físico (API 24+).
5. Clique em **Run 'app'**.

## Como rodar via terminal (Windows / PowerShell)

Na raiz do projeto, execute:

```powershell
./gradlew.bat assembleDebug
```

Se aparecer erro de Java (`JAVA_HOME is not set`), configure a variável para a JBR do Android Studio nesta sessão:

```powershell
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
./gradlew.bat assembleDebug
```

APK gerado em:

- `app/build/outputs/apk/debug/app-debug.apk`

## Estrutura principal

- `app/src/main/java/com/example/provaleonardo/MainActivity.java` - tela de cadastro e validações
- `app/src/main/java/com/example/provaleonardo/ProductListActivity.java` - tela de listagem
- `app/src/main/java/com/example/provaleonardo/ProductAdapter.java` - adapter da RecyclerView
- `app/src/main/java/com/example/provaleonardo/data/Product.java` - entidade Room
- `app/src/main/java/com/example/provaleonardo/data/ProductDao.java` - DAO Room
- `app/src/main/java/com/example/provaleonardo/data/ProductDatabase.java` - database Room

## Observações

- Projeto com tema em tons de azul.
- Banco local usa Room com `allowMainThreadQueries()` para simplificar a execução neste contexto acadêmico.
