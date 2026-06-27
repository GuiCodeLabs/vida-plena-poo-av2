# Mapa de Conceitos

## Introdução

Este documento apresenta onde os principais conceitos de Programação Orientada a Objetos foram aplicados no sistema VidaPlena durante a etapa AV2.

O objetivo é facilitar a leitura da documentação e demonstrar como cada conceito obrigatório aparece na estrutura do projeto, nas classes Java e nos módulos funcionais da clínica.

## Tabela geral de conceitos

| Conceito | Onde aparece | Classes principais | Como foi aplicado |
|---|---|---|---|
| Encapsulamento | Entidades principais e módulos funcionais | `Pessoa`, `Paciente`, `Profissional`, `Consulta`, `Pagamento` | Os dados do domínio ficam organizados em atributos privados ou protegidos e são acessados por getters, setters e métodos próprios. |
| Modificadores de acesso | Entidades, serviços e interfaces | `Pessoa`, `Profissional`, `Pagamento`, `ClinicaServico` | `public`, `private` e `protected` separam o que é exposto, interno ou herdado pelas subclasses. |
| Herança | Pessoas, profissionais e pagamentos | `Pessoa`, `Paciente`, `Profissional`, `Fisioterapeuta`, `Psicologo`, `Pagamento` | Classes especializadas reutilizam atributos e comportamentos das classes base. |
| Classe abstrata | Bases do domínio | `Pessoa`, `Profissional`, `Pagamento` | Classes base representam conceitos gerais e servem como estrutura comum para subclasses. |
| Sobrescrita | Resumos, registros específicos e pagamentos | `Paciente`, `Profissional`, `Fisioterapeuta`, `Psicologo`, `PagamentoDinheiro`, `PagamentoCartao` | Métodos como `exibirResumo()`, `registrarEspecifico(...)` e `calcularValorFinal()` recebem comportamento próprio nas subclasses. |
| Sobrecarga | Construtores e operações com variações | `Paciente`, `Consulta`, `Atendimento`, `Pagamento`, `Relatorio` | Métodos e construtores aceitam diferentes combinações de parâmetros para fluxos simples e completos. |
| Polimorfismo | Profissionais, pagamentos, atendimentos e relatórios | `Profissional`, `Pagamento`, `Exportavel`, subclasses especializadas | Objetos de subclasses podem ser tratados pelo tipo base ou por interface comum. |
| Ligação dinâmica | Chamadas a métodos sobrescritos | `Pessoa`, `Profissional`, `Pagamento`, `Exportavel` | A execução escolhe o método correto de acordo com o objeto real em tempo de execução. |
| Dynamic casting | Listagens e fluxos que dependem do tipo real | `Pessoa`, `Paciente`, `Profissional`, especialidades | `instanceof` permite identificar o tipo real antes de acessar comportamento específico. |
| Interface | Agendamento e exportação | `Agendavel`, `Exportavel`, `Consulta`, `Atendimento`, `Pagamento` | Interfaces definem contratos como `agendar()`, `cancelar()`, `remarcar()` e `exportarDados()`. |
| Associação | Relações entre entidades do domínio | `Consulta`, `Paciente`, `Profissional`, `Pagamento`, `Convenio` | Uma classe conhece outra para executar o fluxo, sem controlar totalmente seu ciclo de vida. |
| Agregação | Horários dos profissionais | `Profissional`, `HorarioDisponivel` | O profissional mantém horários disponíveis, mas o horário é uma informação que pode ser tratada separadamente. |
| Composição | Registro clínico de atendimento | `Atendimento`, `Prontuario` | O prontuário pertence ao atendimento e forma parte do registro clínico daquele atendimento. |
| Coleções | Busca, listagem e controle de duplicidade | `ClinicaServico`, `Paciente`, `Consulta`, `Pagamento`, `Relatorio` | Listas armazenam dados operacionais, `HashSet` controla CPF único e `HashMap` favorece busca por CPF. |
| Exceções personalizadas | Regras de negócio e validações | `PacienteInativoException`, `HorarioIndisponivelException`, `PagamentoInvalidoException`, `ConvenioNaoCobreException` | Exceções específicas representam falhas do domínio da clínica. |
| Tratamento de erros | Menus e operações críticas | `Main`, `ClinicaServico`, exceções do sistema | Erros de entrada, operações inválidas e conflitos são tratados para preservar o fluxo do sistema. |

## Detalhamento por conceito

### Encapsulamento e modificadores de acesso

O encapsulamento aparece nas entidades centrais do sistema. Classes como `Pessoa`, `Paciente`, `Profissional`, `Consulta` e `Pagamento` concentram os dados do domínio e expõem operações coerentes com sua responsabilidade.

Os modificadores de acesso separam atributos internos, métodos públicos e pontos reutilizados por subclasses. Isso deixa a estrutura mais organizada e evita que regras de negócio fiquem espalhadas pelo menu.

### Herança e classes abstratas

A herança evita duplicação de dados e comportamentos. `Pessoa` concentra informações comuns de pacientes e profissionais, enquanto `Profissional` serve como base para especialidades como `Fisioterapeuta`, `Psicologo`, `Nutricionista` e `ClinicoGeral`.

`Pagamento` funciona como base para `PagamentoDinheiro`, `PagamentoCartao` e `PagamentoConvenio`, permitindo representar formas de pagamento diferentes dentro da mesma hierarquia.

### Sobrescrita, polimorfismo e ligação dinâmica

A sobrescrita aparece quando uma subclasse adapta um comportamento herdado. Exemplos importantes são `exibirResumo()`, `registrarEspecifico(...)` e `calcularValorFinal()`.

O polimorfismo permite tratar objetos diferentes por um tipo comum, como `Profissional`, `Pagamento` ou `Exportavel`. A ligação dinâmica garante que, ao chamar um método sobrescrito, a versão executada seja a do objeto real.

### Sobrecarga

A sobrecarga aparece em construtores e métodos que possuem o mesmo nome, mas parâmetros diferentes. Isso permite criar versões simples e completas de objetos como `Paciente`, `Consulta`, `Atendimento` e `Pagamento`.

Em `Relatorio`, a sobrecarga permite gerar relatório geral, por profissional ou por período usando o mesmo nome de método com parâmetros diferentes.

### Interfaces

As interfaces padronizam comportamentos que podem aparecer em classes diferentes. `Agendavel` representa o ciclo de vida de uma consulta, com operações de agendar, cancelar e remarcar.

`Exportavel` padroniza a exportação textual de dados operacionais, permitindo que consultas, atendimentos e pagamentos sejam tratados de forma uniforme nos relatórios.

### Associação, agregação e composição

A associação aparece quando uma classe depende de outra para completar seu fluxo. `Consulta` se relaciona com `Paciente` e `Profissional`; `Pagamento` se relaciona com `Consulta`; `PagamentoConvenio` se relaciona com `Convenio`.

A agregação aparece entre `Profissional` e `HorarioDisponivel`, pois os horários fazem parte da disponibilidade do profissional, mas são informações independentes.

A composição aparece entre `Atendimento` e `Prontuario`, pois o prontuário pertence ao registro clínico daquele atendimento.

### Coleções

As coleções aparecem no armazenamento, busca e listagem de dados do sistema. Listas guardam pacientes, profissionais, consultas, atendimentos e pagamentos.

`HashSet` é usado para representar o controle de CPF único, enquanto `HashMap` representa a busca rápida por CPF no módulo de pacientes.

### Exceções e tratamento de erros

As exceções personalizadas representam falhas específicas do domínio, como paciente inativo, profissional não encontrado, consulta inexistente, horário indisponível, pagamento inválido e convênio sem cobertura.

O tratamento de erros aparece nos fluxos de cadastro, agendamento, atendimento e pagamento, evitando que uma operação inválida comprometa o restante do sistema.

Nos pontos de entrada e operações críticas, o tratamento é feito com `try`, `catch`, `finally`, `throw` e `throws`, conforme a necessidade de cada fluxo.

### Dynamic casting

O dynamic casting aparece nos fluxos em que o sistema precisa identificar o tipo real de um objeto antes de usar um comportamento específico.

Esse conceito se relaciona especialmente com listagens, relatórios e especializações, em que objetos podem ser tratados por um tipo base como `Pessoa`, `Profissional` ou `Pagamento`.

Quando necessário, o uso de `instanceof` permite verificar se o objeto é um `Paciente`, `Profissional` ou alguma especialização antes de realizar o casting.

## Relação dos conceitos com os módulos

| Módulo | Conceitos mais evidentes |
|---|---|
| Pacientes | Encapsulamento, herança, associação, coleções, exceções |
| Profissionais | Herança, classe abstrata, sobrescrita, agregação, polimorfismo |
| Consultas | Interface, associação, controle de estado, coleções, exceções |
| Atendimentos | Composição, associação, interface, sobrecarga, polimorfismo |
| Pagamentos | Classe abstrata, herança, polimorfismo, ligação dinâmica, exceções |
| Relatórios | Interface, sobrecarga, coleções, polimorfismo, exportação |
| Serviço e menus | Tratamento de erros, validação, integração entre módulos |

## Observações finais

Este mapa complementa os arquivos de implementação, os diagramas de classes e as jornadas de usuário.

Enquanto os diagramas mostram a estrutura visual do sistema e as jornadas mostram os fluxos de uso, este documento explica onde cada conceito obrigatório foi aplicado e como ele contribui para a evolução orientada a objetos da Clínica VidaPlena.
