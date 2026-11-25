from fastapi import APIRouter, HTTPException
from app.schemas.capture import CreateCaptureTaskRequest, ApiResponse
from app.services.capture_service import create_mock_task, get_mock_task

router = APIRouter(prefix="/capture", tags=["capture"])

@router.post("/capture-tasks", status_code=202)
async def create_capture_task(req: CreateCaptureTaskRequest) -> ApiResponse:
    task = create_mock_task(req.rawShareText)
    return ApiResponse(code="OK", message="success", data=task, traceId="mock-trace", timestamp="2026-11-21T10:00:00+08:00")

@router.get("/capture-tasks/{taskId}")
async def get_capture_task(taskId: str) -> ApiResponse:
    task = get_mock_task(taskId)
    if task is None:
        raise HTTPException(status_code=404, detail="task not found")
    return ApiResponse(code="OK", message="success", data=task, traceId="mock-trace", timestamp="2026-11-21T10:00:00+08:00")