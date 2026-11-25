_tasks: dict[str, dict] = {}
_counter = 0

def create_mock_task(raw_share_text: str) -> dict:
    global _counter
    _counter += 1
    task_id = f"task_{_counter:04d}"
    _tasks[task_id] = {"taskId": task_id, "status": "PENDING", "videoId": None, "noteId": None, "reused": False}
    return _tasks[task_id]

def get_mock_task(task_id: str) -> dict | None:
    task = _tasks.get(task_id)
    if task is None:
        return None
    # 每次查询推进一个状态，模拟异步流水线
    order = ["PENDING", "PARSING", "TRANSCRIBING", "NOTE_GENERATING", "DONE"]
    idx = order.index(task["status"]) if task["status"] in order else len(order) - 1
    if idx < len(order) - 1:
        task["status"] = order[idx + 1]
    if task["status"] == "DONE":
        task["noteId"] = f"note_{task_id}"
    return task