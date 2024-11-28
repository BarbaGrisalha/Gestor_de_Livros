# Gestor de Livros

## Descrição
Gestor de Livros é um aplicativo Android desenvolvido em Java, projetado para gerenciar informações sobre livros de forma eficiente. O projeto foca na apresentação de uma interface intuitiva com funcionalidades que permitem listar, adicionar e visualizar detalhes de livros.

## Funcionalidades Principais
- **Lista de Livros**: Apresenta uma grade (GridView) com os livros cadastrados.
- **Detalhes do Livro**: Exibe informações detalhadas sobre cada livro selecionado.
- **Adicionação de Livros**: Permite incluir novos livros ao sistema.
- **Gerenciamento**: Integração com o SingletonGestorLivros para centralizar a manipulação dos dados.

## Estrutura do Projeto
- **ListaLivrosFragment**: Responsável por exibir os livros em um layout de grade.
- **SingletonGestorLivros**: Classe singleton que gerencia os dados dos livros.
- **Layouts**: Projetados com XML para garantir responsividade e uma boa experiência do usuário.

## Tecnologias Utilizadas
- **Linguagem**: Java
- **Frameworks**: Android SDK
- **Componentes**: Fragmentos, GridView e Gestores Singleton

## Configuração do Ambiente
1. **IDE Requerida**: Android Studio (versão recomendada: última estável).
2. **SDK**: Android SDK 21 ou superior.
3. **Dependências**: Certifique-se de que o arquivo `build.gradle` está configurado corretamente.

## Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/BarbaGrisalha/Gestor_de_Livros.git
   ```
2. Abra o projeto no Android Studio.
3. Sincronize o Gradle e configure o emulador ou dispositivo físico.
4. Execute o aplicativo pressionando **Run** ou usando o atalho `Shift + F10`.

## Problemas Conhecidos
- **Erro no SingletonGestorLivros**: O método `getLivros` exige um parâmetro `int` mas está sendo chamado sem argumentos. Certifique-se de passar o parâmetro correto.

## Contribuições
Contribuições
    Altamir Rodrigues
    Lucas Siqueira
    João Lains
