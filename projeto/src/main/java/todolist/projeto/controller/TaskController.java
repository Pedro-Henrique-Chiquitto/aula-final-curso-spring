package todolist.projeto.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todolist.projeto.model.Task;
import todolist.projeto.service.TaskService;

import java.util.List;
@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService tasksService;
    // Método para exibir a lista de tarefas
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = tasksService.listAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskList"; // nome da página (Thymeleaf) que vai renderizar a lista
    }
    // Método para exibir a página de criação de tarefa
    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "taskForm"; // nome da página para criar uma nova tarefa
    }
    // Método para salvar uma nova tarefa
    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        tasksService.createTask(task);
        return "redirect:/tasks"; // Redireciona para a lista de tarefas após criação
    }
    // Método para exibir os detalhes de uma tarefa por id
    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        Task task = tasksService.findTaskById(id).getBody();
        model.addAttribute("task", task);
        return "taskDetail"; // nome da página para exibir os detalhes de uma tarefa
    }
    // Método para editar uma tarefa
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = tasksService.findTaskById(id).getBody();
        model.addAttribute("task", task);
        return "taskForm"; // nome da página para editar uma tarefa
    }
    // Método para atualizar uma tarefa
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        tasksService.updateTaskById(task, id);
        return "redirect:/tasks"; // Redireciona para a lista de tarefas após atualização
    }
    // Método para deletar uma tarefa
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        tasksService.deleteById(id);
        return "redirect:/tasks"; // Redireciona para a lista de tarefas após deletar
    }
}