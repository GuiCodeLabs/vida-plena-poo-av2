# Implementação - Pacientes

## Objetivo

Documentar a parte de pacientes da arquitetura final da AV2 do VidaPlena. Esta frente cobre a base de `Pessoa`, o cadastro de `Paciente`, a associação com `Convenio`, a validação de CPF e os tratamentos de erro ligados ao paciente.

## Jornadas atendidas

- Jornada 1 - Cadastro Simplificado de Paciente.
- Jornada 2 - Cadastro Completo de Paciente e Controle de Duplicidade.
- Jornada 3 - Complementação de Cadastro.
- Jornada 12 - Desativação de Paciente.
- Jornada 14 - Cadastro com Validação Robusta de Dados.
- Jornada 27 - Busca Instantânea de Pacientes.
- Jornada 28 - Controle de Unicidade de CPF.

## Classes envolvidas

- `Pessoa`: classe abstrata com dados comuns, como nome, CPF, telefone e data de nascimento.
- `Paciente`: especialização de `Pessoa`, com status ativo/inativo e convênio associado.
- `Convenio`: representa o convênio do paciente, percentual de cobertura e especialidades cobertas.
- `PacienteNaoEncontradoException`: exceção para busca de paciente inexistente.
- `PacienteInativoException`: exceção para impedir operações indevidas com paciente inativo.
- `ConvenioNaoCobreException`: exceção para situações em que o convênio não cobre determinada especialidade.
- `ClinicaServico`: concentra as regras de cadastro, busca, validação de CPF e desativação.
- `Main`: expõe as opções de acesso ao módulo de pacientes.

## Conceitos aplicados

- Herança: `Paciente` herda os dados comuns de `Pessoa`.
- Classe abstrata: `Pessoa` define a base comum e o método `exibirResumo()`.
- Encapsulamento: atributos privados com acesso por getters e setters.
- Associação: `Paciente` possui uma referência para `Convenio`.
- Coleções: `HashMap` para busca rápida por CPF e `HashSet` para impedir CPF duplicado.
- Exceções personalizadas: erros de paciente, paciente inativo e convênio sem cobertura.
- Sobrescrita: `Paciente` implementa seu próprio `exibirResumo()`.

## Diagrama

![Pacientes, Pessoa e Convênio](../diagramas/pacientes-pessoa-convenio.png)

Arquivo do diagrama: `docs/diagramas/pacientes-pessoa-convenio.png`.

O PNG acima foi gerado a partir do Mermaid abaixo.

## Código Mermaid

```mermaid
classDiagram
direction TB

class Pessoa {
    <<abstract>>
    -String nome
    -String cpf
    -String telefone
    -String dataNascimento
    +Pessoa(String nome, String cpf)
    +Pessoa(String nome, String cpf, String telefone, String dataNascimento)
    +getNome() String
    +setNome(String nome) void
    +getCpf() String
    +setCpf(String cpf) void
    +getTelefone() String
    +setTelefone(String telefone) void
    +getDataNascimento() String
    +setDataNascimento(String dataNascimento) void
    +exibirResumo() String
}

class Paciente {
    -boolean ativo
    -Convenio convenio
    +Paciente(String nome, String cpf)
    +Paciente(String nome, String cpf, String telefone, String dataNascimento)
    +isAtivo() boolean
    +setAtivo(boolean ativo) void
    +getConvenio() Convenio
    +setConvenio(Convenio convenio) void
    +desativar() void
    +exibirResumo() String
}

class Convenio {
    -String nome
    -double percentualCobertura
    -List~String~ especialidadesCobertas
    +Convenio(String nome, double percentualCobertura)
    +getNome() String
    +setNome(String nome) void
    +getPercentualCobertura() double
    +setPercentualCobertura(double percentualCobertura) void
    +cobreEspecialidade(String especialidade) boolean
}

class ClinicaServico {
    -HashMap pacientesPorCpf
    -HashSet cpfsCadastrados
    +cadastrarPaciente(Paciente paciente) void
    +buscarPacientePorCpf(String cpf) Paciente
    +complementarPaciente(String cpf) void
    +desativarPaciente(String cpf) void
    +validarCpfUnico(String cpf) void
}

class Main {
    +menuPacientes() void
    +cadastrarPaciente() void
    +complementarPaciente() void
    +buscarPaciente() void
    +listarPacientes() void
    +desativarPaciente() void
}

class PacienteNaoEncontradoException {
    +PacienteNaoEncontradoException(String mensagem)
}

class PacienteInativoException {
    +PacienteInativoException(String mensagem)
}

class ConvenioNaoCobreException {
    +ConvenioNaoCobreException(String mensagem)
}

class Exception

Pessoa <|-- Paciente
Paciente --> Convenio : possui
Main ..> ClinicaServico : usa
ClinicaServico ..> Paciente : gerencia
ClinicaServico ..> Convenio : valida
Exception <|-- PacienteNaoEncontradoException
Exception <|-- PacienteInativoException
Exception <|-- ConvenioNaoCobreException
ClinicaServico ..> PacienteNaoEncontradoException : lanca
ClinicaServico ..> PacienteInativoException : lanca
ClinicaServico ..> ConvenioNaoCobreException : lanca
```
