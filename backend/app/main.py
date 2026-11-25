from fastapi import FastAPI
from app.api.v1.capture import router as capture_router

app = FastAPI(title="AI StudyMate API", version="0.1.0")
app.include_router(capture_router, prefix="/api/v1")