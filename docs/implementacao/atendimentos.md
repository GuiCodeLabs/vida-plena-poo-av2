
# Implementação - Atendimentos

## Objetivo

Documentar a parte de atendimentos da arquitetura final da AV2 do VidaPlena. Esta frente cobre o registro clínico de uma consulta realizada, incluindo observações, diagnóstico, procedimentos e prontuário.

O módulo representa o atendimento como o resultado clínico de uma consulta, conectando o registro do profissional ao histórico do paciente.

## Jornadas atendidas

- Jornada 8 - Registro de Atendimento.
- Jornada 24 - Registro de Atendimento com Prontuário.
- Jornada 25 - Registro Especializado de Atendimento Psicológico.

## Classes envolvidas

- `Atendimento`: representa o registro clínico feito após uma consulta.
- `Prontuario`: reúne observações, diagnóstico, procedimentos e data do registro.
- `Consulta`: fornece a consulta que deu origem ao atendimento.
- `Profissional`: participa do atendimento e pode registrar informações específicas da sua área.
- `Psicologo`: especialização de profissional usada no registro clínico especializado.
- `Exportavel`: interface para padronizar a exportação textual do atendimento.
- `OperacaoInvalidaException`: exceção para situações em que o atendimento não pode ser registrado.
- `ClinicaServico`: concentra as regras de registro e consulta dos atendimentos.
- `Main`: expõe as opções de acesso ao módulo de atendimentos.

## Conceitos aplicados

- Composição: `Atendimento` contém um `Prontuario`, que pertence ao registro clínico daquele atendimento.
- Associação: `Atendimento` se relaciona com `Consulta` e `Profissional`.
- Interface: `Exportavel` padroniza a saída textual do atendimento.
- Sobrecarga: o atendimento pode ser criado com observações, com diagnóstico ou com procedimentos completos.
- Polimorfismo: o profissional real pode executar um registro específico conforme sua especialidade.
- Sobrescrita: especializações de `Profissional` podem adaptar `registrarEspecifico(...)`.
- Exceção personalizada: `OperacaoInvalidaException` representa tentativas inválidas de registrar atendimento.

## Diagrama

![Atendimentos e Prontuário](../diagramas/atendimentos.png)

Arquivo do diagrama: `docs/diagramas/atendimentos.png`.

O Mermaid abaixo é a fonte editável do diagrama.

## Código Mermaid

```mermaid
classDiagram
direction TB

class Exportavel {
    <<interface>>
    +exportarDados() String
}

class Atendimento {
    -Consulta consulta
    -Profissional profissional
    -Prontuario prontuario
    +Atendimento(Consulta consulta, String observacoes)
    +Atendimento(Consulta consulta, String observacoes, String diagnostico)
    +Atendimento(Consulta consulta, String observacoes, String diagnostico, List~String~ procedimentos)
    +adicionarProcedimento(String procedimento) void
    +adicionarProcedimentos(List~String~ procedimentos) void
    +exibirResumo() String
    +exportarDados() String
}

class Prontuario {
    -String observacoes
    -String diagnostico
    -List~String~ procedimentos
    -String dataRegistro
    +Prontuario(String observacoes)
    +Prontuario(String observacoes, String diagnostico)
    +adicionarProcedimento(String procedimento) void
    +getObservacoes() String
    +getDiagnostico() String
    +getProcedimentos() List~String~
    +getDataRegistro() String
}

class Consulta {
    -String data
    -String horario
    -String status
    +realizar() void
    +exibirResumo() String
}

class Profissional {
    <<abstract>>
    -String especialidade
    +registrarEspecifico(Atendimento atendimento) void
    +exibirResumo() String
}

class Psicologo {
    -String abordagem
    +registrarEspecifico(Atendimento atendimento) void
}

class ClinicaServico {
    +registrarAtendimento(Consulta consulta, Profissional profissional, String observacoes) Atendimento
    +registrarAtendimentoCompleto(Consulta consulta, Profissional profissional, String observacoes, String diagnostico, List~String~ procedimentos) Atendimento
    +buscarAtendimentoPorConsulta(Consulta consulta) Atendimento
}

class Main {
    +menuAtendimentos() void
    +registrarAtendimento() void
}

class OperacaoInvalidaException {
    +OperacaoInvalidaException(String mensagem)
}

class Exception

Exportavel <|.. Atendimento
Atendimento *-- Prontuario : compoe
Atendimento --> Consulta : registra
Atendimento --> Profissional : responsavel
Profissional <|-- Psicologo
Main ..> ClinicaServico : usa
ClinicaServico ..> Atendimento : gerencia
ClinicaServico ..> Consulta : valida
ClinicaServico ..> Profissional : aciona registro especifico
Exception <|-- OperacaoInvalidaException
ClinicaServico ..> OperacaoInvalidaException : lanca
```
