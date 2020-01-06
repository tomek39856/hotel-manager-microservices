import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomSelectionComponent } from './room-selection.component';

describe('RoomSelectionComponent', () => {
  let component: RoomSelectionComponent;
  let fixture: ComponentFixture<RoomSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
