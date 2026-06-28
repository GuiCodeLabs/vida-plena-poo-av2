# Sistema Clínica VidaPlena - POO AV2

![Java](https://img.shields.io/badge/Java-Puro-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Terminal](https://img.shields.io/badge/Execu%C3%A7%C3%A3o-Terminal-4D4D4D?style=flat-square&logo=windows-terminal&logoColor=white)
![AV2](https://img.shields.io/badge/AV2-Refatoração%20POO-16A34A?style=flat-square)
![Documentação](https://img.shields.io/badge/Documentação-Diagramas%20%7C%20Jornadas%20%7C%20Conceitos-2563EB?style=flat-square)

Sistema acadêmico desenvolvido em **Java puro** para a disciplina de **Programação Orientada a Objetos**. O projeto representa a evolução da Clínica VidaPlena para a etapa AV2, com foco em organização por módulos, documentação técnica, jornadas de usuário, diagramas de classes e aplicação dos conceitos de POO previstos no roteiro da disciplina.

---

## 1. Visão geral

A **Clínica VidaPlena** é um sistema de gestão de clínica multidisciplinar executado pelo terminal. O sistema organiza fluxos de pacientes, profissionais, consultas, atendimentos, pagamentos e relatórios.

O repositório possui dois níveis importantes:

- o **núcleo funcional atual**, concentrado principalmente em `Main.java` e nas classes operacionais usadas pelos menus;
- a **estrutura de refatoração AV2**, composta por documentação, diagramas, interfaces, exceções e classes planejadas para a arquitetura final orientada a objetos.

Essa separação existe porque a AV2 evolui o projeto anterior: parte do sistema já está funcional no terminal, enquanto algumas classes da nova modelagem ainda aparecem como base de refatoração.

---

## 2. Estado atual do projeto

| Parte | Situação atual |
|---|---|
| Menu principal | Implementado em `Main.java`, com acesso a pacientes, profissionais, consultas, atendimentos, pagamentos e relatórios. |
| Núcleo funcional | Usa `Paciente`, `Profissional`, `Consulta`, `Atendimento`, `Pagamento` e `Relatorio` para executar os fluxos principais no terminal. |
| Armazenamento atual | Usa vetores em memória, como `Paciente[]`, `Profissional[]`, `Consulta[]`, `Atendimento[]` e `Pagamento[]`. |
| Refatoração AV2 | Possui classes e interfaces planejadas, como `Pessoa`, `ClinicaServico`, `Convenio`, `HorarioDisponivel`, `Prontuario`, `Agendavel` e `Exportavel`. |
| Exceções personalizadas | As classes de exceção existem no projeto e estão documentadas para integração aos fluxos de validação. |
| Documentação | A pasta `docs` contém jornadas, mapa de conceitos, diagramas e documentos de implementação por módulo. |
| Compilação completa | Ainda possui pendências em subclasses esqueleto que precisam chamar corretamente os construtores das superclasses. |

---

## 3. Funcionalidades atuais no terminal

### Pacientes

O menu de pacientes permite:

- cadastrar paciente com dados básicos;
- cadastrar paciente com dados completos;
- complementar cadastro;
- buscar paciente por CPF;
- listar pacientes cadastrados;
- desativar paciente;
- impedir cadastro com CPF duplicado;
- registrar nome de convênio no cadastro.

Classes usadas diretamente no fluxo atual:

- `Paciente`
- `Main`

Classes relacionadas na documentação AV2:

- `Pessoa`
- `Convenio`
- `ClinicaServico`
- `PacienteNaoEncontradoException`
- `PacienteInativoException`
- `ConvenioNaoCobreException`

---

### Profissionais

O menu de profissionais permite:

- cadastrar profissional;
- atualizar cadastro profissional;
- registrar especialidade;
- informar registro profissional;
- informar valor da consulta;
- cadastrar dias disponíveis;
- listar profissionais;
- filtrar profissionais por especialidade.

Classes usadas diretamente no fluxo atual:

- `Profissional`
- `Main`

Classes relacionadas na documentação AV2:

- `Pessoa`
- `Fisioterapeuta`
- `Psicologo`
- `Nutricionista`
- `ClinicoGeral`
- `HorarioDisponivel`
- `ClinicaServico`
- `ProfissionalNaoEncontradoException`

---

### Consultas

O menu de consultas permite:

- agendar consulta escolhendo o profissional;
- agendar consulta buscando por especialidade;
- cancelar consulta;
- remarcar consulta;
- listar consultas;
- buscar consultas por CPF do paciente;
- validar paciente inativo;
- verificar conflito de horário;
- verificar disponibilidade do profissional pelo dia da semana.

Classes usadas diretamente no fluxo atual:

- `Consulta`
- `Paciente`
- `Profissional`
- `Main`

Classes e interfaces relacionadas na documentação AV2:

- `Agendavel`
- `HorarioDisponivel`
- `ClinicaServico`
- `ConsultaNaoEncontradaException`
- `HorarioIndisponivelException`
- `OperacaoInvalidaException`
- `PacienteInativoException`

---

### Atendimentos

O menu de atendimentos permite:

- registrar atendimento vinculado a uma consulta;
- registrar observações clínicas;
- registrar diagnóstico;
- adicionar procedimentos;
- marcar a consulta como realizada;
- exibir resumo do atendimento.

Classes usadas diretamente no fluxo atual:

- `Atendimento`
- `Consulta`
- `Main`

Classes e interfaces relacionadas na documentação AV2:

- `Prontuario`
- `Profissional`
- `Psicologo`
- `Exportavel`
- `ClinicaServico`
- `OperacaoInvalidaException`

---

### Pagamentos

O menu de pagamentos permite:

- registrar pagamento direto;
- calcular pagamento automático com base no valor da consulta;
- aplicar desconto;
- registrar multa;
- registrar pagamento em dinheiro, cartão ou convênio;
- limitar parcelas de cartão;
- listar pagamentos registrados.

Classes usadas diretamente no fluxo atual:

- `Pagamento`
- `Consulta`
- `Paciente`
- `Profissional`
- `Main`

Classes e exceções relacionadas na documentação AV2:

- `PagamentoDinheiro`
- `PagamentoCartao`
- `PagamentoConvenio`
- `Convenio`
- `ClinicaServico`
- `PagamentoInvalidoException`
- `ConvenioNaoCobreException`

---

### Relatórios

O menu de relatórios permite:

- gerar relatório geral;
- gerar relatório por profissional;
- gerar relatório por período;
- gerar resumo financeiro;
- listar diagnósticos registrados nos atendimentos;
- consultar faturamento, multas e cancelamentos.

Classes usadas diretamente no fluxo atual:

- `Relatorio`
- `Consulta`
- `Atendimento`
- `Pagamento`
- `Main`

Classes e interfaces relacionadas na documentação AV2:

- `Exportavel`
- `ClinicaServico`
- `Paciente`
- `Profissional`

---

## 4. Conceitos de POO no projeto

O projeto atual possui uma parte executável mais simples e uma modelagem AV2 documentada com os conceitos obrigatórios. A tabela abaixo diferencia o que já aparece no núcleo atual e o que está preparado ou documentado para a refatoração.

| Conceito | Situação no projeto |
|---|---|
| Encapsulamento | Documentado na arquitetura AV2. No código atual, algumas classes operacionais ainda usam atributos públicos e precisam ser ajustadas na refatoração. |
| Modificadores de acesso | Aparecem parcialmente, como no método privado de apoio em `Relatorio`; a documentação prevê uso mais completo de `private`, `protected` e `public`. |
| Herança | As subclasses de profissionais e pagamentos já existem, mas ainda precisam ser integradas corretamente aos construtores das classes base. |
| Classes abstratas | `Pessoa` existe como base abstrata planejada para pacientes e profissionais. |
| Interfaces | `Agendavel` e `Exportavel` existem como contratos planejados para consultas e exportações. |
| Sobrecarga | Aparece no núcleo atual em construtores e métodos de `Paciente`, `Profissional`, `Consulta`, `Atendimento`, `Pagamento` e `Relatorio`. |
| Sobrescrita | Está prevista na modelagem AV2 para resumos, registros específicos e cálculo de pagamentos especializados. |
| Polimorfismo | Está documentado principalmente para profissionais, pagamentos e objetos exportáveis. |
| Ligação dinâmica | Relacionada aos métodos sobrescritos previstos na refatoração AV2. |
| Dynamic casting | Documentado para fluxos que dependem do tipo real do objeto, especialmente especializações profissionais. |
| Associação | Já aparece nos fluxos por CPF, índice de consulta e vínculo entre consulta, atendimento e pagamento. |
| Agregação | Documentada entre `Profissional` e `HorarioDisponivel`. |
| Composição | Documentada entre `Atendimento` e `Prontuario`. |
| Coleções | O núcleo atual usa vetores; a documentação AV2 prevê uso de `List`, `HashSet` e `HashMap` para busca e controle de duplicidade. |
| Exceções personalizadas | As classes existem e estão documentadas; a integração completa aos menus ainda faz parte da refatoração. |
| Tratamento de erros | O núcleo atual usa validações condicionais; a documentação AV2 prevê uso de `try`, `catch`, `finally`, `throw` e `throws` nos pontos críticos. |

O detalhamento completo está em [`docs/mapa-conceitos.md`](./docs/mapa-conceitos.md).

---

## 5. Estrutura do projeto

```text
├── 📁 docs
│   ├── 📁 diagramas
│   │   ├── 🖼️ atendimentos.png
│   │   ├── 🖼️ consultas-agendamento.png
│   │   ├── 🖼️ pacientes-pessoa-convenio.png
│   │   ├── 🖼️ pagamentos.png
│   │   ├── 🖼️ profissionais-especialidades.png
│   │   └── 🖼️ relatorios.png
│   ├── 📁 implementacao
│   │   ├── 📝 atendimentos.md
│   │   ├── 📝 consultas.md
│   │   ├── 📝 pacientes.md
│   │   ├── 📝 pagamentos.md
│   │   ├── 📝 profissionais.md
│   │   └── 📝 relatorios.md
│   ├── 📕 Projeto de Disciplina - Descrito Etapa AV2.pdf
│   ├── 📕 Projeto de Disciplina - Jornadas de Usuário.pdf
│   ├── 📕 Projeto de Disciplina - Roteiro de Refatoração.pdf
│   ├── 📝 diagrama-classes.md
│   ├── 📝 jornadas-usuario.md
│   └── 📝 mapa-conceitos.md
├── 📁 src
│   ├── ☕ Agendavel.java
│   ├── ☕ Atendimento.java
│   ├── ☕ ClinicaServico.java
│   ├── ☕ ClinicoGeral.java
│   ├── ☕ Consulta.java
│   ├── ☕ ConsultaNaoEncontradaException.java
│   ├── ☕ Convenio.java
│   ├── ☕ ConvenioNaoCobreException.java
│   ├── ☕ Exportavel.java
│   ├── ☕ Fisioterapeuta.java
│   ├── ☕ HorarioDisponivel.java
│   ├── ☕ HorarioIndisponivelException.java
│   ├── ☕ Main.java
│   ├── ☕ Nutricionista.java
│   ├── ☕ OperacaoInvalidaException.java
│   ├── ☕ Paciente.java
│   ├── ☕ PacienteInativoException.java
│   ├── ☕ PacienteNaoEncontradoException.java
│   ├── ☕ Pagamento.java
│   ├── ☕ PagamentoCartao.java
│   ├── ☕ PagamentoConvenio.java
│   ├── ☕ PagamentoDinheiro.java
│   ├── ☕ PagamentoInvalidoException.java
│   ├── ☕ Pessoa.java
│   ├── ☕ Profissional.java
│   ├── ☕ ProfissionalNaoEncontradoException.java
│   ├── ☕ Prontuario.java
│   ├── ☕ Psicologo.java
│   └── ☕ Relatorio.java
├── ⚙️ .gitignore
└── 📝 README.md
```

---

## 6. Como compilar e executar

O projeto não utiliza Maven, Gradle, banco de dados, frameworks ou interface gráfica. A execução é feita diretamente pelo terminal.

### Pré-requisito

- Java Development Kit, JDK 8 ou superior.

### Núcleo funcional atual

Enquanto a refatoração AV2 completa não é finalizada, o núcleo funcional atual pode ser compilado com as classes usadas diretamente pelos menus:

```powershell
javac src\Main.java src\Paciente.java src\Profissional.java src\Consulta.java src\Atendimento.java src\Pagamento.java src\Relatorio.java
```

Depois da compilação:

```powershell
java -cp src Main
```

### Verificação completa da AV2

O comando abaixo verifica todas as classes do diretório `src`:

```powershell
javac src\*.java
```

No estado atual, essa verificação aponta pendências nas subclasses esqueleto de `Profissional` e `Pagamento`, que precisam chamar os construtores corretos das superclasses.

### Limpeza dos arquivos compilados

No Windows PowerShell:

```powershell
Remove-Item src\*.class
```

---

## 7. Como navegar pelo sistema

Ao executar o programa, o menu principal apresenta:

```text
1 - Pacientes
2 - Profissionais
3 - Consultas
4 - Atendimentos
5 - Pagamentos
6 - Relatorios
0 - Sair
```

Cada módulo possui um submenu próprio para cadastrar, consultar, atualizar ou listar as informações relacionadas.

---

## 8. Documentação do projeto

A documentação complementar está organizada na pasta `docs`.

| Documento | Descrição |
|---|---|
| [`docs/diagrama-classes.md`](./docs/diagrama-classes.md) | Catálogo visual dos diagramas de classes por módulo. |
| [`docs/jornadas-usuario.md`](./docs/jornadas-usuario.md) | Mapeamento das jornadas de usuário 1 a 30. |
| [`docs/mapa-conceitos.md`](./docs/mapa-conceitos.md) | Relação entre os conceitos de POO e as classes do projeto. |
| [`docs/implementacao/pacientes.md`](./docs/implementacao/pacientes.md) | Documentação do módulo de pacientes. |
| [`docs/implementacao/profissionais.md`](./docs/implementacao/profissionais.md) | Documentação do módulo de profissionais. |
| [`docs/implementacao/consultas.md`](./docs/implementacao/consultas.md) | Documentação do módulo de consultas. |
| [`docs/implementacao/atendimentos.md`](./docs/implementacao/atendimentos.md) | Documentação do módulo de atendimentos. |
| [`docs/implementacao/pagamentos.md`](./docs/implementacao/pagamentos.md) | Documentação do módulo de pagamentos. |
| [`docs/implementacao/relatorios.md`](./docs/implementacao/relatorios.md) | Documentação do módulo de relatórios. |

Os códigos Mermaid ficam nos arquivos de implementação. As imagens exportadas dos diagramas ficam em [`docs/diagramas`](./docs/diagramas).

---

## 9. Principais classes de apoio da AV2

| Grupo | Classes |
|---|---|
| Interfaces | `Agendavel`, `Exportavel` |
| Base de pessoas | `Pessoa`, `Paciente`, `Profissional` |
| Especialidades | `Fisioterapeuta`, `Psicologo`, `Nutricionista`, `ClinicoGeral` |
| Agenda | `Consulta`, `HorarioDisponivel` |
| Atendimento | `Atendimento`, `Prontuario` |
| Pagamentos | `Pagamento`, `PagamentoDinheiro`, `PagamentoCartao`, `PagamentoConvenio` |
| Relatórios | `Relatorio` |
| Serviço | `ClinicaServico` |
| Exceções | `PacienteNaoEncontradoException`, `PacienteInativoException`, `ProfissionalNaoEncontradoException`, `ConsultaNaoEncontradaException`, `HorarioIndisponivelException`, `OperacaoInvalidaException`, `PagamentoInvalidoException`, `ConvenioNaoCobreException` |

---

## 👨‍💻 Autores

Desenvolvido por **Guilherme Cavalcante**, **Pedro Henrique**, **Pedro Antonio**, **Arllan Leopoldino** e **Felipe Gabriel**

> ✅ Projeto Concluído.
> Feito com ❤️ muito ☕ e muita dedicação 🚀

