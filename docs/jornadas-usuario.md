# Jornadas de Usuário

## Introdução

Este documento apresenta o mapeamento das jornadas de usuário do sistema VidaPlena na etapa AV2.

As jornadas 1 a 13 representam fluxos já existentes na AV1, agora organizados dentro da documentação final e relacionados aos conceitos de Programação Orientada a Objetos usados no projeto. As jornadas 14 a 30 representam novas necessidades da clínica, integradas aos módulos existentes sem substituir os processos anteriores.

## Resumo por módulo

| Módulo | Jornadas relacionadas |
|---|---|
| Pacientes | 1, 2, 3, 12, 14, 18, 27, 28 |
| Profissionais | 4, 16, 17, 29 |
| Consultas | 5, 6, 7, 9, 10, 18, 19, 30 |
| Atendimentos | 8, 24, 25 |
| Pagamentos | 11, 20, 21, 22, 23 |
| Relatórios e exportação | 13, 15, 26 |
| Tratamento de erros | 14, 18, 19, 20, 22, 27, 28, 30 |

> O status indica que a jornada foi contemplada no escopo documental e na organização dos módulos da AV2.

## Jornadas mapeadas

| Nº | Jornada | Ator | Módulo | Classes principais | Onde aparece no sistema | Status |
|---|---|---|---|---|---|---|
| 1 | Cadastro Simplificado de Paciente | Recepcionista | Pacientes | `Pessoa`, `Paciente`, `ClinicaServico`, `Main` | Menu de pacientes, cadastro inicial com nome e CPF. | Coberta no escopo AV2 |
| 2 | Cadastro Completo de Paciente e Controle de Duplicidade | Recepcionista | Pacientes | `Pessoa`, `Paciente`, `Convenio`, `ClinicaServico` | Menu de pacientes, cadastro completo e controle de CPF repetido. | Coberta no escopo AV2 |
| 3 | Complementação de Cadastro | Recepcionista | Pacientes | `Paciente`, `Convenio`, `ClinicaServico` | Menu de pacientes, atualização de dados complementares do paciente. | Coberta no escopo AV2 |
| 4 | Cadastro e Atualização de Profissionais | Administração | Profissionais | `Pessoa`, `Profissional`, `ClinicaServico`, `Main` | Menu de profissionais, cadastro e atualização de registro, valor e disponibilidade. | Coberta no escopo AV2 |
| 5 | Agendamento de Consulta por Profissional | Recepcionista | Consultas | `Consulta`, `Paciente`, `Profissional`, `Agendavel` | Menu de consultas, agendamento escolhendo diretamente o profissional. | Coberta no escopo AV2 |
| 6 | Agendamento por Especialidade | Recepcionista | Consultas | `Consulta`, `Profissional`, `HorarioDisponivel`, `ClinicaServico` | Menu de consultas, busca de profissional disponível por especialidade. | Coberta no escopo AV2 |
| 7 | Tratamento de Conflitos de Agenda | Recepcionista | Consultas | `Consulta`, `Profissional`, `HorarioIndisponivelException` | Menu de consultas, validação de horário ocupado para o profissional. | Coberta no escopo AV2 |
| 8 | Registro de Atendimento | Profissional de saúde | Atendimentos | `Atendimento`, `Consulta`, `Profissional`, `ClinicaServico` | Menu de atendimentos, registro clínico vinculado a uma consulta. | Coberta no escopo AV2 |
| 9 | Cancelamento de Consulta | Recepcionista | Consultas | `Consulta`, `ConsultaNaoEncontradaException`, `OperacaoInvalidaException` | Menu de consultas, cancelamento de consulta existente. | Coberta no escopo AV2 |
| 10 | Remarcação de Consulta | Recepcionista | Consultas | `Consulta`, `Profissional`, `HorarioIndisponivelException` | Menu de consultas, criação de novo horário para uma consulta agendada. | Coberta no escopo AV2 |
| 11 | Processamento de Pagamentos | Financeiro | Pagamentos | `Pagamento`, `Consulta`, `ClinicaServico`, `Main` | Menu de pagamentos, registro de pagamento direto ou automático. | Coberta no escopo AV2 |
| 12 | Desativação de Paciente | Recepcionista | Pacientes | `Paciente`, `PacienteInativoException`, `ClinicaServico` | Menu de pacientes, alteração do status do paciente para inativo. | Coberta no escopo AV2 |
| 13 | Emissão de Relatórios Gerenciais | Gestor | Relatórios e exportação | `Relatorio`, `Consulta`, `Atendimento`, `Pagamento` | Menu de relatórios, relatório geral e resumo financeiro. | Coberta no escopo AV2 |
| 14 | Cadastro com Validação Robusta de Dados | Recepcionista | Pacientes | `Pessoa`, `Paciente`, `ClinicaServico`, `Main` | Menu de pacientes, validação dos dados usados no cadastro. | Coberta no escopo AV2 |
| 15 | Relatório Unificado de Cadastros | Gestor | Relatórios e exportação | `Relatorio`, `Paciente`, `Profissional`, `Exportavel` | Módulo de relatórios, consolidação de informações cadastrais. | Coberta no escopo AV2 |
| 16 | Cadastro de Fisioterapeuta | Administração | Profissionais | `Profissional`, `Fisioterapeuta`, `ClinicaServico` | Menu de profissionais, cadastro de especialidade de fisioterapia. | Coberta no escopo AV2 |
| 17 | Cadastro de Psicólogo | Administração | Profissionais | `Profissional`, `Psicologo`, `ClinicaServico` | Menu de profissionais, cadastro de especialidade de psicologia. | Coberta no escopo AV2 |
| 18 | Agendamento para Paciente Inativo | Recepcionista | Consultas | `Consulta`, `Paciente`, `PacienteInativoException` | Menu de consultas, bloqueio de agendamento para paciente inativo. | Coberta no escopo AV2 |
| 19 | Tratamento de Conflitos de Horário | Recepcionista | Consultas | `Consulta`, `HorarioDisponivel`, `HorarioIndisponivelException` | Menu de consultas, verificação de conflito por data e horário. | Coberta no escopo AV2 |
| 20 | Verificação de Cobertura de Convênio | Financeiro | Pagamentos | `Convenio`, `PagamentoConvenio`, `ConvenioNaoCobreException` | Módulo de pagamentos, validação da cobertura antes de aplicar convênio. | Coberta no escopo AV2 |
| 21 | Processamento de Pagamento em Dinheiro | Financeiro | Pagamentos | `Pagamento`, `PagamentoDinheiro`, `ClinicaServico` | Menu de pagamentos, aplicação da regra de pagamento em dinheiro. | Coberta no escopo AV2 |
| 22 | Processamento de Pagamento em Cartão | Financeiro | Pagamentos | `Pagamento`, `PagamentoCartao`, `PagamentoInvalidoException` | Menu de pagamentos, registro de parcelas e validação de pagamento em cartão. | Coberta no escopo AV2 |
| 23 | Processamento de Pagamento por Convênio | Financeiro | Pagamentos | `PagamentoConvenio`, `Convenio`, `ConvenioNaoCobreException` | Menu de pagamentos, cálculo com cobertura do convênio. | Coberta no escopo AV2 |
| 24 | Registro de Atendimento com Prontuário | Profissional de saúde | Atendimentos | `Atendimento`, `Prontuario`, `Consulta`, `Exportavel` | Menu de atendimentos, registro clínico com diagnóstico e procedimentos. | Coberta no escopo AV2 |
| 25 | Registro Especializado de Atendimento Psicológico | Psicólogo | Atendimentos | `Atendimento`, `Psicologo`, `Profissional`, `Prontuario` | Módulo de atendimentos, registro específico conforme especialidade. | Coberta no escopo AV2 |
| 26 | Exportação de Dados Operacionais | Gestor | Relatórios e exportação | `Exportavel`, `Relatorio`, `Consulta`, `Atendimento`, `Pagamento` | Módulo de relatórios, saída textual padronizada dos dados operacionais. | Coberta no escopo AV2 |
| 27 | Busca Instantânea de Pacientes | Recepcionista | Pacientes | `Paciente`, `ClinicaServico`, `PacienteNaoEncontradoException` | Menu de pacientes, busca por CPF. | Coberta no escopo AV2 |
| 28 | Controle de Unicidade de CPF | Recepcionista | Pacientes | `Pessoa`, `Paciente`, `ClinicaServico`, `HashSet`, `HashMap` | Menu de pacientes, impedimento de cadastro com CPF duplicado. | Coberta no escopo AV2 |
| 29 | Gestão de Horários Disponíveis | Administração | Profissionais | `Profissional`, `HorarioDisponivel`, `ClinicaServico` | Menu de profissionais, cadastro e atualização de dias ou horários disponíveis. | Coberta no escopo AV2 |
| 30 | Tratamento Completo de Operações Inválidas | Usuário do sistema | Consultas | `OperacaoInvalidaException`, `Consulta`, `ClinicaServico`, `Main` | Fluxos de consulta, atendimento e pagamento que exigem validação de estado. | Coberta no escopo AV2 |

## Integração entre AV1 e AV2

As jornadas novas da AV2 foram integradas aos módulos já existentes da AV1. O cadastro de pacientes continua atendendo os fluxos simples e completos, mas agora também considera validação robusta, controle de CPF, busca rápida e tratamento de exceções.

Da mesma forma, consultas, atendimentos, pagamentos e relatórios mantêm os fluxos principais, mas passam a ser documentados com os conceitos de orientação a objetos aplicados no projeto: herança, interfaces, composição, agregação, polimorfismo, coleções e exceções personalizadas.

## Como testar as jornadas pelo menu

As jornadas podem ser localizadas a partir do menu principal da aplicação:

- Pacientes: cadastro, complementação, busca, controle de CPF e desativação.
- Profissionais: cadastro, atualização, especialidades e horários disponíveis.
- Consultas: agendamento, agendamento por especialidade, cancelamento, remarcação e conflitos de horário.
- Atendimentos: registro clínico, prontuário e registro especializado.
- Pagamentos: dinheiro, cartão, convênio, descontos, parcelas e cobertura.
- Relatórios: relatórios gerais, filtros por profissional, filtros por período, resumo financeiro e exportação.

As jornadas de tratamento de erros aparecem integradas aos módulos, principalmente em cadastros, consultas, pagamentos e operações que dependem do status de paciente, profissional, consulta ou convênio.
